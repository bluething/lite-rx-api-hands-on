package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
public class Part08OtherOperations {

//========================================================================================

	// TODO Create a Flux of user from Flux of username, firstname and lastname.
	// zip(Publisher<? extends String> source1, Publisher<? extends String> source2, Publisher<? extends String> source3)
	// Zip three sources together, that is to say wait for all the sources to emit one element and combine these elements once into a Tuple3.
	Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {
		return Flux
				.zip(usernameFlux, firstnameFlux, lastnameFlux)
				.map(u -> new User(u.getT1(), u.getT2(), u.getT3()));
	}

//========================================================================================

	// TODO Return the mono which returns its value faster
	// first(Iterable<? extends Mono<? extends T>> monos)
	// Pick the first available result coming from any of the given monos and populate a new Mono. 
	Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
		return Mono.first(mono1, mono2);
	}

//========================================================================================

	// TODO Return the flux which returns the first value faster
	// first(Iterable<? extends Publisher<? extends I>> sources)
	// Pick the first Publisher to emit any signal (onNext/onError/onComplete) and replay all signals from that Publisher, effectively behaving like the fastest of these competing sources. 
	Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
		return Flux.first(flux1, flux2);
	}

//========================================================================================

	// TODO Convert the input Flux<User> to a Mono<Void> that represents the complete signal of the flux
	// then()
	// Return a Mono<Void> that completes when this Flux completes.
	Mono<Void> fluxCompletion(Flux<User> flux) {
		return flux.then();
	}

//========================================================================================

	// TODO Return a valid Mono of user for null input and non null input user (hint: Reactive Streams do not accept null values)
	// justOrEmpty(@Nullable T data)
	// Create a new Mono that emits the specified item if non null otherwise only emits onComplete.
	Mono<User> nullAwareUserToMono(User user) {
		return Mono.justOrEmpty(user);
	}

//========================================================================================

	// TODO Return the same mono passed as input parameter, expect that it will emit User.SKYLER when empty
	// defaultIfEmpty(T defaultV)
	// Provide a default single value if this mono is completed without any data
	Mono<User> emptyToSkyler(Mono<User> mono) {
		return mono.defaultIfEmpty(User.SKYLER);
	}

}
