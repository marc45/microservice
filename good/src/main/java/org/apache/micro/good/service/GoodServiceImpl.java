package org.apache.micro.good.service;

import org.apache.micro.good.domain.GoodRequest;
import org.apache.micro.good.domain.GoodResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/good")
public class GoodServiceImpl implements GoodService {

	@Override
	public GoodResponse save(GoodRequest requesst) {
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
}
