package org.apache.micro.good.service;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.apache.base.dao.table.Insert;
import org.apache.base.dao.table.Select;
import org.apache.micro.good.domain.Good;
import org.apache.micro.good.domain.GoodModel;
import org.apache.micro.good.domain.GoodPicture;
import org.apache.micro.good.domain.GoodPropertyValue;
import org.apache.micro.good.mapper.GoodMapper;
import org.apache.micro.good.mapper.GoodPictureMapper;
import org.apache.micro.good.mapper.GoodPropertyValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Path("/good")
public class GoodResource {

	private static final String HEADER_ACCEPT = "Accept";
	private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
	private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
	private static final String HEADER_CONTENT_TYPE = "Content-Type";
	private static final String HEADER_GZIP_VALUE = "gzip";
	private static final String HEADER_JSON_VALUE = "json";

	@Autowired
	private GoodMapper goodMapper ;

	@Autowired
	private GoodPictureMapper goodPictureMapper ;

	@Autowired
	private GoodPropertyValueMapper goodPropertyValueMapper ;

	@Autowired
	private EurekaClient discoveryEurekaClient ;

	private ExecutorService service = Executors.newFixedThreadPool(10) ;

	@GET
	@Path("/get/{appName}")
	public Response helloWorld(@PathParam("appName") String appName){
		Application applicatioin = discoveryEurekaClient.getApplication("productApplication") ;
		List<InstanceInfo> instanceInfos = applicatioin.getInstances() ;
		Response response = Response.ok(instanceInfos).header(HEADER_CONTENT_TYPE, MediaType.APPLICATION_JSON).build() ;
		return response;
	}

	@POST
	@Path("/save")

	public void save(GoodModel requesst) {

		Good good = getGood() ;
		String goodId = good.getId() ;
		Insert insert =  Insert.newInsert(new Good[]{good}) ;

		List<GoodPicture> goodPictures = getGoodPictures(goodId) ;
		Insert pictureInsert = Insert.newInsert(goodPictures.toArray(new GoodPicture[goodPictures.size()])) ;

		List<GoodPropertyValue> goodPropertyValues = getGoodPropertyValues(goodId) ;
		Insert propertyValueInsert = Insert.newInsert(goodPropertyValues.toArray(new GoodPropertyValue[goodPropertyValues.size()])) ;

		goodMapper.insert(insert) ;
//		if(Math.random() > 0.01){
//			throw new RuntimeException("error message") ;
//		}
		goodPictureMapper.insert(pictureInsert) ;
		goodPropertyValueMapper.insert(propertyValueInsert) ;

	}
	@Transactional
	public void save(){
		//事务相关的在该方法中执行
	}

	@GET
	@Path("/{id}")
	public Response find(@PathVariable("id") String id) {
		//根据名称查询商品
		//

		List objects1 = null ;
		Select goodSelect = (Select) Select.newSelect(Good.class).where().equals("id",id);

		Select pictureSelect = (Select) Select.newSelect(GoodPicture.class).where().equals("goodId",id);
		Select propertyValue = (Select) Select.newSelect(GoodPropertyValue.class).where().equals("goodId",id);
		long start = System.currentTimeMillis() ;
		for(int i =0 ;i < 100 ;i++){
			objects1 = goodMapper.select(goodSelect) ;
			objects1 = goodPictureMapper.select(pictureSelect) ;
			objects1 = goodPropertyValueMapper.select(propertyValue) ;
		}


		System.out.println("cost:"+(System.currentTimeMillis()-start)) ;

		start = System.currentTimeMillis() ;
		CountDownLatch countDownLatch = new CountDownLatch(100) ;
		for(int i =0 ;i < 100 ;i++){
			Observable.fromArray(new Select[]{goodSelect,pictureSelect,propertyValue}).flatMap(item ->{
				return Observable.just(item).subscribeOn(Schedulers.from(service)).map(value -> {
					List objects = null ;
					if(value.getClz().getName().equals(Good.class.getName())  ){
						objects = goodMapper.select(value) ;
					}else if(value.getClz().getName().equals(GoodPicture.class.getName())  ){
						objects = goodPictureMapper.select(value) ;
					}else if(value.getClz().getName().equals(GoodPropertyValue.class.getName()) ){
						objects = goodPropertyValueMapper.select(value) ;
					}
					return objects ;
				}) ;
			}).observeOn(Schedulers.computation()).subscribe(value -> {},error ->{throw new RuntimeException(error) ;},()->{countDownLatch.countDown();}); ;


		}

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("cost2:"+(System.currentTimeMillis()-start)) ;
//		Observable.just().sub
		// 并发子表查询


		return null;
	}

	public void update(GoodModel request) {

	}

	public Good getGood(){
		Good good = new Good();
		good.setId(UUID.randomUUID().toString());
		good.setName(good.getId());
		good.setBrandName("adfafadfasfdasdfaadfsafasd");
		return good ;
	}

	public List<GoodPicture> getGoodPictures(String goodId){
		List<GoodPicture> goodPictures = new ArrayList<>(10) ;
		for(int i = 0  ;i < 10 ;i++){
			GoodPicture goodPicture = new GoodPicture() ;
			goodPicture.setId(UUID.randomUUID().toString());
			goodPicture.setGoodId(goodId);
			goodPicture.setName("adfafafadsfasdfadfasfdasdfaasdfasdfasdfsadfsdf");
			goodPicture.setUrl("adfasfdllllllllllllllllllllllllllllllllllllllipqrqwerqiwuerpqwurpkfa;fkasjfas fkasaf");
			goodPictures.add(goodPicture) ;
		}
		return goodPictures ;
	}

	public List<GoodPropertyValue> getGoodPropertyValues(String goodId){
		List<GoodPropertyValue> goodPropertyValues = new ArrayList<>(10) ;
		for(int i = 0  ;i < 10 ;i++){
			GoodPropertyValue goodPropertyValue = new GoodPropertyValue() ;
			goodPropertyValue.setId(UUID.randomUUID().toString());
			goodPropertyValue.setGoodId(goodId);
			goodPropertyValue.setProperty("adfasdfdaf");
			goodPropertyValue.setValue("adfasfdlllllllllllllllllllllllllwerqiwuerpqwurpkfa;fkasjfas fkasaf");
			goodPropertyValues.add(goodPropertyValue) ;
		}
		return goodPropertyValues ;
	}
}
