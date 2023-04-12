package pro.idugalic.axonpolymorphism;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.idugalic.axonpolymorphism.command.api.FlyCommand;
import pro.idugalic.axonpolymorphism.command.api.HatchEagleCommand;

import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final CommandGateway commandGateway;
    
    @Autowired
    public RestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping("/api/hatchEagle")
    public String hatchEagle() {
        commandGateway.send(new HatchEagleCommand(UUID.randomUUID().toString()));
        return "Ok";
    }

    @GetMapping("/api/fly")
    public String fly(String aggregateId) {
        commandGateway.send(new FlyCommand(aggregateId, 5L));
        return "Ok";
    }
}
