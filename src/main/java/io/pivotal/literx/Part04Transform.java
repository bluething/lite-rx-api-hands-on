package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {

//========================================================================================

	// TODO Capitalize the user username, firstname and lastname
	// map(Function<? super T,? extends R> mapper)
	// Transform the item emitted by this Mono by applying a synchronous function to it.
	Mono<User> capitalizeOne(Mono<User> mono) {
		return mono.map(u -> new User(
				u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()
				));
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName
	// map(Function<? super T,? extends V> mapper)
	// Transform the items emitted by this Flux by applying a synchronous function to each item. 
	Flux<User> capitalizeMany(Flux<User> flux) {
		return flux.map(u -> new User(
				u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()
				));
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
	// flatMap(Function<? super T,? extends Publisher<? extends R>> mapper)
	// Transform the elements emitted by this Flux asynchronously into Publishers, then flatten these inner publishers into a single Flux through merging, which allow them to interleave.
	Flux<User> asyncCapitalizeMany(Flux<User> flux) {
		return flux.flatMap(u -> asyncCapitalizeUser(u));
	}

	Mono<User> asyncCapitalizeUser(User u) {
		return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
	}

}
