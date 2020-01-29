/*
 * Copyright 2002-2016 the original author or authors.
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

package org.aopalliance.aop;

/**
 * 增强（advice)主要包括如下五种类型
 * 1. 前置增强(BeforeAdvice)：在目标方法执行前实施增强
 * 2. 后置增强(AfterAdvice)：在目标方法执行后（无论是否抛出遗产）实施增强
 * 3. 环绕增强(MethodInterceptor)：在目标方法执行前后实施增强
 * 4. 异常抛出增强(ThrowsAdvice)：在目标方法抛出异常后实施增强
 * 5. 返回增强（AfterReturningAdvice）：在目标方法正常返回后实施增强
 * 6. 引介增强(IntroductionIntercrptor)：在目标类中添加一些新的方法和属性
 *
 * Tag interface for Advice. Implementations can be any type
 * of advice, such as Interceptors.
 *
 * @author Rod Johnson
 * @version $Id: Advice.java,v 1.1 2004/03/19 17:02:16 johnsonr Exp $
 */
public interface Advice {

}
