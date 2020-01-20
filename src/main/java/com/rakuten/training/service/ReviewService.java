package com.rakuten.training.service;

import java.util.List;

import com.rakuten.training.domain.Review;

public interface ReviewService {
	
	public int addReviewToProduct(int productId,Review toBeAdded);
	public List<Review> getByProductId(int productId);

}
