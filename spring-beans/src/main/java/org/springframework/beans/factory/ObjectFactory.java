/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * 它的目的也是作为一个工厂，来生成 Object（这个接口只有一个方法 getObject()）。这个接口一般被
 * 用来，包装一个 factory，通过个这工厂来返回一个新实例（prototype 类型）。这个接口和 FactoryBean
 * 有点像，但 FactoryBean 的实现是被当做一个 SPI（Service Provider Interface）实例来使用在
 * BeanFactory 里面；ObjectFactory 的实现一般被用来注入到其它 Bean 中，作为 API 来使用。就
 * 像 ObjectFactoryCreatingFactoryBean 的 例 子 ， 它 的 返 回 值 就 是 一 个 ObjectFactory ， 这 个
 * ObjectFactory 被注入到了 Bean 中，在 Bean 通过这个接口的实例，来取得我们想要的 Bean。
 * 
 * 总的来说，FactoryBean 和 ObjectFactory 都是用来取得 Bean，但使用的方法和地方不同，
 * FactoryBean 被配置好后，Spring 调用 getObject()方法来取得 Bean，ObjectFactory 配置好后，
 * 在 Bean 里面可以取得 ObjectFactory 实例，需要我们手动来调用 getObject()来取得 Bean
 *
 * Defines a factory which can return an Object instance
 * (possibly shared or independent) when invoked.
 *
 * <p>This interface is typically used to encapsulate a generic factory which
 * returns a new instance (prototype) of some target object on each invocation.
 *
 * <p>This interface is similar to {@link FactoryBean}, but implementations
 * of the latter are normally meant to be defined as SPI instances in a
 * {@link BeanFactory}, while implementations of this class are normally meant
 * to be fed as an API to other beans (through injection). As such, the
 * {@code getObject()} method has different exception handling behavior.
 *
 * @author Colin Sampaleanu
 * @since 1.0.2
 * @param <T> the object type
 * @see FactoryBean
 */
@FunctionalInterface
public interface ObjectFactory<T> {

	/**
	 * Return an instance (possibly shared or independent)
	 * of the object managed by this factory.
	 * @return the resulting instance
	 * @throws BeansException in case of creation errors
	 */
	T getObject() throws BeansException;

}
