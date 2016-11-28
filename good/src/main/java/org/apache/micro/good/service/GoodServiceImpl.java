package org.apache.micro.good.service;

import org.apache.base.dao.table.Select;
import org.apache.micro.good.domain.*;
import org.apache.micro.good.mapper.GoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path="/good")
public class GoodServiceImpl implements GoodService {

	@Autowired
	private GoodMapper goodMapper ;

	@Autowired
	private GoodPicture goodPicture ;

	@Autowired
	private GoodProperty goodProperty ;

	@Override
	public void save(GoodModel model) {



	}

	@Override
	@RequestMapping(path="/{id}")
	public List<GoodModel> find(long id) {
		//根据名称查询商品
		//
		Select goodSelect = (Select) Select.newSelect(Good.class).where().equals("id",id);
		goodMapper.select(goodSelect) ;

		// 并发子表查询

		return null;
	}

	@Override
	public void update(GoodModel request) {

	}
}
