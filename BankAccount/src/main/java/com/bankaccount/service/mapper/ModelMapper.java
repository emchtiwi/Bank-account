package com.bankaccount.service.mapper;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Enhancement of basic Model Mapper.
 * 
 *
 */
@Component
public class ModelMapper extends org.modelmapper.ModelMapper {

	/**
	 * Constructor.
	 */
	public ModelMapper() {
		super();
	}

	/**
	 * Map collection to a new collection of specified object type.
	 *
	 * @param collection
	 *            Collection to map.
	 * @param type
	 *            Target object type.
	 * @param <D>
	 *            Target object type.
	 * @return Mapped list.
	 */
	public <D> Collection<D> mapCollection(final List<?> collection, final Class<D> type) {
		final Collection<D> result = new ArrayList<>();
		for (final Object entry : collection) {
			result.add(map(entry, type));
		}
		return result;
	}

}
