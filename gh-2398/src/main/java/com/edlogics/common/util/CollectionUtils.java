/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.common.util;

import static ch.lambdaj.Lambda.exists;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.selectFirst;
import static ch.lambdaj.collection.LambdaCollections.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.ArgumentConverter;

/**
 * Collection Utilities needed, but not found in any common libraries. e.g. Apache Commons
 *
 * @author Christopher Savory
 *
 */
@Validated
public class CollectionUtils {

	/**
	 * Takes an original list and figures out how it was updated (added, updated, removed) using the
	 * argument.
	 * 
	 * This will not return a new list, but rather return the original. Meaning it is meant to be used
	 * with an api like JPA where you would want a removal to be observed.
	 *
	 * @param originalCollection
	 * @param newCollection
	 * @param argument An argument defined using the {@link Lambda#on(Class)} method
	 * @param propertiesToCopy an array of strings that represents properties that get copied over for items that match the comparisonProperty
	 * @return
	 */
	public static <T, A> Collection<T> diffAndMergeCollection( @NotEmpty Collection<T> originalCollection, @NotEmpty Collection<T> newCollection, A comparisonProperty,
			@NotEmpty String... propertiesToCopy ) {
		Collection<T> newCollectionCopy = with( newCollection ).clone();

		//First Remove the objects that are not in the new list
		for ( Iterator<T> it = originalCollection.iterator(); it.hasNext(); ) {
			T object = it.next();
			if ( !exists( newCollection, having( comparisonProperty, is( equalTo( new ArgumentConverter<T, A>( comparisonProperty ).convert( object ) ) ) ) ) ) {
				it.remove();
			}
		}

		//Next, copy properties on objects that match
		for ( Iterator<T> it = newCollectionCopy.iterator(); it.hasNext(); ) {
			T newObject = it.next();
			T originalObject = selectFirst( originalCollection, having( comparisonProperty, is( equalTo( new ArgumentConverter<T, A>( comparisonProperty ).convert( newObject ) ) ) ) );

			if ( originalObject != null ) {
				for ( String property : propertiesToCopy ) {
					try {
						PropertyUtils.setProperty( originalObject, property, PropertyUtils.getProperty( newObject, property ) );
					} catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
						throw new RuntimeException( e );
					}
				}
				it.remove();
			}
		}

		//Add the remaining new properties to the original collection
		originalCollection.addAll( newCollectionCopy );

		return originalCollection;
	}

}
