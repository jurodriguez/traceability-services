package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.TraceabilityRequestDto;
import com.pragma.powerup.application.dto.response.TraceabilityResponseDto;
import com.pragma.powerup.application.handler.ITraceabilityHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/traceability")
@RequiredArgsConstructor
public class TraceabilityRestController {

    private final ITraceabilityHandler traceabilityHandler;

    @Operation(summary = "Add a new Traceability")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Traceability created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Traceability already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveTraceability(@Valid @RequestBody TraceabilityRequestDto traceabilityRequestDto) {
        traceabilityHandler.saveTraceability(traceabilityRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all Traceability")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Traceability returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = TraceabilityResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/getAllTraceability")
    public ResponseEntity<List<TraceabilityResponseDto>> getAllTraceability(@RequestParam Long orderId) {
        return ResponseEntity.ok(traceabilityHandler.getAllTraceability(orderId));
    }

}