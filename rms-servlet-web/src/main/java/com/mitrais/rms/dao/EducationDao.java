package com.mitrais.rms.dao;

import java.util.List;

import com.mitrais.rms.model.Education;

/**
 * Provides interface specific to {@link Education} data
 */
public interface EducationDao extends Dao<Education, Long>{
	List<Education> findByUserId(Long id);
}
