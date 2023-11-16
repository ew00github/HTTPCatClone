package com.evan.HTTPCatClone;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.evan.HTTPCatClone.repository.HttpStatusRepository;
import com.evan.HTTPCatClone.service.HttpStatusService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HttpStatusServiceTests {

    @Mock
    private HttpStatusRepository httpStatusRepository;

    @InjectMocks
    private HttpStatusService httpStatusService;

    @Test
    void shouldReturnTrueIfNewStatusAddedViaGetStatusGroupMethod() {
        when(httpStatusService.getByStatus(Mockito.anyString())).thenReturn(new HttpStatus("585", new byte[] {}));
        List<HttpStatus> result = httpStatusService.getStatusGroup("500");
        HttpStatus testStatusNew = new HttpStatus("585", new byte[] {});
        assertThat(result).contains(testStatusNew);
    }

    @Test
    void shouldReturnResponseStatusExceptionIfStatusGroupInvalid(){
        assertThrows(ResponseStatusException.class, () -> httpStatusService.getStatusGroup("599"));
    }
}