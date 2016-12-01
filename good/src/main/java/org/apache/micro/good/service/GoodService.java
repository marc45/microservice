package org.apache.micro.good.service;

import org.apache.micro.good.domain.GoodModel;

import java.util.List;

public interface GoodService {

	void save(GoodModel requesst) ;

	List<GoodModel> find(long id) ;

	void update(GoodModel request) ;
}
