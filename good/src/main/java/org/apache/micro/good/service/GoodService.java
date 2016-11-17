package org.apache.micro.good.service;

import org.apache.micro.good.domain.GoodRequest;
import org.apache.micro.good.domain.GoodResponse;

public interface GoodService {

	GoodResponse save(GoodRequest requesst) ;
	
	GoodResponse find(GoodRequest request) ;

	GoodResponse update(GoodRequest request) ;
}
