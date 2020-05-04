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

package io.pivotal.literx;

import java.time.Duration;
import java.util.function.Supplier;

import org.assertj.core.api.Assertions;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to use StepVerifier to test Mono, Flux or any other kind of Reactive Streams Publisher.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/test/release/api/reactor/test/StepVerifier.html">StepVerifier Javadoc</a>
 */
public class Part03StepVerifier {

	//========================================================================================

	// TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
	// expectNext(T t)
	// Expect the next element received to be equal to the given value.
	void expectFooBarComplete(Flux<String> flux) {
		StepVerifier.create(flux)
		.expectNext("foo","bar")
		.verifyComplete();
	}

	//========================================================================================

	// TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
	// expectNext(T t)
	// Expect the next element received to be equal to the given value.
	// verifyError(Class<? extends Throwable> clazz)
	// Trigger the verification, expecting an error of the specified type as terminal event.
	// This is a convenience method that calls StepVerifier.verify() in addition to the expectation.
	// Explicitly use the expect method and verification method separately if you need something more specific (like activating logging or changing the default timeout).
	void expectFooBarError(Flux<String> flux) {
		StepVerifier.create(flux)
		.expectNext("foo","bar")
		.verifyError(RuntimeException.class);
	}

	//========================================================================================

	// TODO Use StepVerifier to check that the flux parameter emits a User with "swhite"username
	// and another one with "jpinkman" then completes successfully.
	// expectNextMatches(Predicate<? super T> predicate)
	// Expect an element and evaluate with the given predicate.
	// assertNext(Consumer<? super T> assertionConsumer)
	// Expect an element and consume it with the given consumer, usually performing assertions on it (eg. using Hamcrest, AssertJ or JUnit assertion methods).
	// Alias for consumeNextWith(Consumer) for better discoverability of that use case.
	// Any AssertionErrors thrown by the consumer will be rethrown during verification.
	void expectSkylerJesseComplete(Flux<User> flux) {
		StepVerifier.create(flux)
		.expectNextMatches(user -> user.getUsername().equals("swhite"))
		.assertNext(user -> Assertions.assertThat(user.getUsername()).isEqualToIgnoringCase("jpinkman"))
		.verifyComplete();
	}

	//========================================================================================

	// TODO Expect 10 elements then complete and notice how long the test takes.
	// expectNextCount(long count)
	// Expect to received count (the number of emitted items to expect) elements, starting from the previous expectation or onSubscribe.
	void expect10Elements(Flux<Long> flux) {
		StepVerifier.create(flux)
		.expectNextCount(10)
		.verifyComplete();
	}

	//========================================================================================

	// TODO Expect 3600 elements at intervals of 1 second, and verify quicker than 3600s
	// by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice how long the test takes
	// withVirtualTime(Supplier<? extends Publisher<? extends T>> scenarioSupplier)
	// scenarioSupplier - a mandatory supplier of the Publisher to subscribe to and verify.
	// In order for operators to use virtual time, they must be invoked from within the lambda.
	void expect3600Elements(Supplier<Flux<Long>> supplier) {
		StepVerifier.withVirtualTime(supplier)
		.thenAwait(Duration.ofHours(1))
		.expectNextCount(3600)
		.verifyComplete();
	}

	@SuppressWarnings("unused")
	private void fail() {
		throw new AssertionError("workshop not implemented");
	}

}
