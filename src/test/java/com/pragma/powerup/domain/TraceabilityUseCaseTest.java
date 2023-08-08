package com.pragma.powerup.domain;

import com.pragma.powerup.domain.enums.EOrderStatuses;
import com.pragma.powerup.domain.model.TraceabilityModel;
import com.pragma.powerup.domain.spi.ITraceabilityPersistencePort;
import com.pragma.powerup.domain.usecase.TraceabilityUseCase;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class TraceabilityUseCaseTest {

    @InjectMocks
    TraceabilityUseCase traceabilityUseCase;

    @Mock
    ITraceabilityPersistencePort traceabilityPersistencePort;

    @Test
    void mustSaveTraceability() {
        TraceabilityModel traceabilityModel = getTraceability();

        traceabilityUseCase.saveTraceability(traceabilityModel);
        Mockito.verify(traceabilityPersistencePort, Mockito.times(1)).saveTraceability(Mockito.any(TraceabilityModel.class));
    }

    @Test
    void getAllTraceability() {
        Long orderId = 1L;
        List<TraceabilityModel> traceabilityModelList = new ArrayList<>();
        traceabilityModelList.add(getTraceability());
        Mockito.when(traceabilityPersistencePort.getAllTraceability(orderId)).thenReturn(traceabilityModelList);

        List<TraceabilityModel> result = traceabilityUseCase.getAllTraceability(orderId);

        assertEquals(traceabilityModelList, result);
    }

    @Test
    void getAllTraceabilityWithNoDataFoundException() {
        Long orderId = 1L;
        List<TraceabilityModel> traceabilityModelList = new ArrayList<>();
        Mockito.when(traceabilityPersistencePort.getAllTraceability(orderId)).thenReturn(traceabilityModelList);

        assertThrows(NoDataFoundException.class, () -> traceabilityUseCase.getAllTraceability(orderId));
    }

    @Test
    void timeDifferenceForOrders() {
        Long orderId = 1L;
        String diff = "6:28:0";

        Mockito.when(traceabilityPersistencePort.getTraceabilityByOrderIdAndNewStatus(orderId, EOrderStatuses.PENDING.getName())).thenReturn(getTraceability());
        Mockito.when(traceabilityPersistencePort.getTraceabilityByOrderIdAndNewStatus(orderId, EOrderStatuses.READY.getName())).thenReturn(getTraceability2());

        String result = traceabilityUseCase.timeDifferenceForOrders(orderId);

        assertEquals(diff, result);
    }

    @Test
    void timeDifferenceForOrdersWithNoDataFoundException() {
        Long orderId = 1L;

        Mockito.when(traceabilityPersistencePort.getTraceabilityByOrderIdAndNewStatus(orderId, EOrderStatuses.PENDING.getName())).thenReturn(getTraceability());
        Mockito.when(traceabilityPersistencePort.getTraceabilityByOrderIdAndNewStatus(orderId, EOrderStatuses.READY.getName())).thenReturn(null);

        assertThrows(NoDataFoundException.class, ()-> traceabilityUseCase.timeDifferenceForOrders(orderId));
    }

    private TraceabilityModel getTraceability() {
        TraceabilityModel traceability = new TraceabilityModel();
        traceability.setId("as32da213sd1sa213a1sd3");
        traceability.setOrderId("1");
        traceability.setCustomerId("2");
        traceability.setCustomerEmail("customer@gmail.com");
        traceability.setDate("2023-08-03 11:47:00");
        traceability.setLastStatus(EOrderStatuses.IN_PREPARATION.getName());
        traceability.setNewStatus(EOrderStatuses.DELIVERED.getName());
        traceability.setEmployeeId("3");
        traceability.setEmployeeEmail("employee@gmail.com");

        return traceability;
    }

    private TraceabilityModel getTraceability2() {
        TraceabilityModel traceability = new TraceabilityModel();
        traceability.setOrderId("1");
        traceability.setDate("2023-08-03 18:15:00");

        return traceability;
    }
}
