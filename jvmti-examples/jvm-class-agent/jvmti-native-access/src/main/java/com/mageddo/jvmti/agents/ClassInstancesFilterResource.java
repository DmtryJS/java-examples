package com.mageddo.jvmti.agents;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mageddo.jvmti.agents.entrypoint.vos.InstanceFilterReq;
import com.mageddo.jvmti.classdelegate.ClassInstanceService;
import lombok.SneakyThrows;
import net.metzweb.tinyserver.Request;
import net.metzweb.tinyserver.Response;
import net.metzweb.tinyserver.TinyServer;

public class ClassInstancesFilterResource implements Response {

  private final ClassInstanceService classInstanceService;
  private final ObjectMapper objectMapper;

  public ClassInstancesFilterResource(
    TinyServer tinyServer,
    ClassInstanceService classInstanceService,
    ObjectMapper objectMapper
  ) {
    this.classInstanceService = classInstanceService;
    this.objectMapper = objectMapper;
    tinyServer.post("/class-instances/filter", this);
  }
  @Override
  @SneakyThrows
  public void callback(Request request) {
    this.classInstanceService.filter(
      this.objectMapper.readValue(request.getData(), InstanceFilterReq.class)
      .toInstanceFilter()
    );
  }
}
