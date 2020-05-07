/*
 * Copyright (c) 2011-2017 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to deal with errors.
 *
 * @author Sebastien Deleuze
 * @see Exceptions#propagate(Throwable)
 */
public class Part07Errors {

//========================================================================================

	// TODO Return a Mono<User> containing User.SAUL when an error occurs in the input Mono, else do not change the input Mono.
	// onErrorResume(Class<E> type, Function<? super E,? extends Mono<? extends T>> fallback)
	// Subscribe to a fallback publisher when an error matching the given type occurs, using a function to choose the fallback depending on the error.
	// just(T data)
	// Create a new Mono that emits the specified item, which is captured at instantiation time.
	Mono<User> betterCallSaulForBogusMono(Mono<User> mono) {
		return mono.onErrorResume(e -> Mono.just(User.SAUL));
	}

//========================================================================================

	// TODO Return a Flux<User> containing User.SAUL and User.JESSE when an error occurs in the input Flux, else do not change the input Flux.
	// onErrorResume(Function<? super Throwable,? extends Publisher<? extends T>> fallback)
	// Subscribe to a returned fallback publisher when any error occurs, using a function to choose the fallback depending on the error. 
	// just(T... data)
	// Create a Flux that emits the provided elements and then completes. 
	Flux<User> betterCallSaulAndJesseForBogusFlux(Flux<User> flux) {
		return flux.onErrorResume(e -> Flux.just(User.SAUL, User.JESSE));
	}

//========================================================================================

	// TODO Implement a method that capitalizes each user of the incoming flux using the
	// #capitalizeUser method and emits an error containing a GetOutOfHereException error
	// Exceptions.propagate(Throwable t)
	// Prepare an unchecked RuntimeException that should be propagated downstream through Subscriber.onError(Throwable).
	// This method invokes throwIfFatal(Throwable).
	Flux<User> capitalizeMany(Flux<User> flux) {
		return flux.map(user -> {
			try {
				return capitalizeUser(user);
			}
			catch (GetOutOfHereException e) {
				throw Exceptions.propagate(e);
			}
		});
	}

	User capitalizeUser(User user) throws GetOutOfHereException {
		if (user.equals(User.SAUL)) {
			throw new GetOutOfHereException();
		}
		return new User(user.getUsername(), user.getFirstname(), user.getLastname());
	}

	protected final class GetOutOfHereException extends Exception {
	    private static final long serialVersionUID = 0L;
	}

}
