package com.mageddo.zipkin.chairfactory.consumer;

import brave.Tracing;
import com.mageddo.zipkin.Topics;
import com.mageddo.zipkin.chairfactory.service.ChairFactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FactoryMountedChairMDB {

	private final ChairFactoryService chairFactoryService;

	@KafkaListener(topics = Topics.FACTORY_MOUNTED_CHAIR_DELIVERY)
	public void consume(String msg){
		Tracing
			.currentTracer()
			.startScopedSpan("factory: painting request")
			.tag("msg", msg)
		;
		chairFactoryService.requestChairPaint(msg);
		Tracing.currentTracer().currentSpan().finish();
	}
}
