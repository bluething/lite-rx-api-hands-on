package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.BlockingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * Learn how to call blocking code from Reactive one with adapted concurrency strategy for
 * blocking code that produces or receives data.
 *
 * For those who know RxJava:
 *  - RxJava subscribeOn = Reactor subscribeOn
 *  - RxJava observeOn = Reactor publishOn
 *  - RxJava Schedulers.io <==> Reactor Schedulers.elastic
 *
 * @author Sebastien Deleuze
 * @see Flux#subscribeOn(Scheduler)
 * @see Flux#publishOn(Scheduler)
 * @see Schedulers
 */
public class Part11BlockingToReactive {

//========================================================================================

	// TODO Create a Flux for reading all users from the blocking repository deferred until the flux is subscribed, and run it with an elastic scheduler
	// Flux.defer(Supplier<? extends Publisher<User>> supplier)
	// Lazily supply a Publisher every time a Subscription is made on the resulting Flux, 
	// so the actual source instantiation is deferred until each subscribe and the Supplier can create a subscriber-specific instance.
	// If the supplier doesn't generate a new instance however, this operator will effectively behave like from(Publisher).
	// Flux.subscribeOn(Scheduler scheduler)
	// Run subscribe, onSubscribe and request on a specified Scheduler's Scheduler.Worker.
	// As such, placing this operator anywhere in the chain will also impact the execution context of onNext/onError/onComplete signals from the beginning of the chain up to the next occurrence of a publishOn.
	Flux<User> blockingRepositoryToFlux(BlockingRepository<User> repository) {
		return Flux.defer(
				() -> Flux.fromIterable(repository.findAll()))
				.subscribeOn(Schedulers.elastic());
	}

//========================================================================================

	// TODO Insert users contained in the Flux parameter in the blocking repository using an elastic scheduler and return a Mono<Void> that signal the end of the operation
	// Flux.publishOn(Scheduler scheduler)
	// Run onNext, onComplete and onError on a supplied Scheduler Worker.
	// This operator influences the threading context where the rest of the operators in the chain below it will execute, up to a new occurrence of publishOn.
	Mono<Void> fluxToBlockingRepository(Flux<User> flux, BlockingRepository<User> repository) {
		return flux
				.publishOn(Schedulers.elastic())
				.doOnNext(repository::save).then();
	}

}
