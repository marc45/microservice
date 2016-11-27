package org.apache.micro.good.service;

import org.apache.base.dao.table.Insert;
import org.apache.micro.good.domain.*;
import org.apache.micro.good.mapper.GoodMapper;
import org.apache.micro.good.mapper.GoodPictureMapper;
import org.apache.micro.good.mapper.GoodPropertyValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/good")
public class GoodServiceImpl implements GoodService {

	@Autowired
	private GoodMapper goodMapper ;

	@Autowired
	private GoodPictureMapper goodPictureMapper ;

	@Autowired
	private GoodPropertyValueMapper goodPropertyValueMapper ;

	@RequestMapping(path = "/test")
	public String helloWorld(){
		return "hello world" ;
	}

	@Override
	@RequestMapping(path="/save")
	@Transactional
	public GoodResponse save(GoodRequest requesst) {

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

		return null;
	}

	@Override
	public GoodResponse find(GoodRequest request) {
		//根据名称查询商品
		//
		// 并发查询

		return null;
	}

	@Override
	public GoodResponse update(GoodRequest request) {
		return null;
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
