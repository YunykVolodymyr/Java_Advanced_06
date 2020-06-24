package ua.yunyk.service.impl;

import java.util.List;

import ua.yunyk.dao.BucketDao;
import ua.yunyk.dao.impl.BucketDaoImpl;
import ua.yunyk.domain.Bucket;
import ua.yunyk.service.BucketService;

public class BucketServiceImpl implements BucketService {

	private BucketDao bucketDao;

	public BucketServiceImpl() {
		bucketDao = new BucketDaoImpl();
	}

	@Override
	public Bucket create(Bucket t) {
		return bucketDao.create(t);
	}

	@Override
	public Bucket read(Integer id) {
		return bucketDao.read(id);
	}

	@Override
	public Bucket update(Bucket t) {
		return bucketDao.update(t);
	}

	@Override
	public void delete(Integer id) {
		bucketDao.delete(id);
	}

	@Override
	public List<Bucket> readAll() {
		return bucketDao.readAll();
	}

	@Override
	public Bucket readByParameter(Object parameter) {
		return bucketDao.readByParameter(parameter);
	}

}
