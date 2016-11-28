package org.apache.micro.good.service;

import org.apache.micro.good.domain.Good;
import org.apache.micro.good.domain.GoodModel;
import org.apache.micro.good.domain.GoodRequest;
import org.apache.micro.good.domain.GoodResponse;

import java.util.List;

public interface GoodService {

	void save(GoodModel requesst) ;

	List<GoodModel> find(long id) ;

	void update(GoodModel request) ;
}
