package com.evan.HTTPCatClone;

import com.evan.HTTPCatClone.model.HttpStatus;
import com.evan.HTTPCatClone.repository.HttpStatusRepository;
import com.evan.HTTPCatClone.service.HttpStatusService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HttpStatusServiceTest {

    @Mock
    private HttpStatusRepository httpStatusRepository;

    @InjectMocks
    private HttpStatusService httpStatusService;

    @Test
    public void testGetStatusGroup_whenValidStatusGiven_thenReturnStatusGroup() {
        when(httpStatusRepository.findByStatus("200")).thenReturn(createHttpStatus("200"));
        when(httpStatusRepository.findByStatus("201")).thenReturn(createHttpStatus("201"));

        List<HttpStatus> statusList = httpStatusService.getStatusGroup("200");

        Assert.assertEquals(2, statusList.size());
        Mockito.verify(httpStatusRepository).findByStatus("200");
        Mockito.verify(httpStatusRepository).findByStatus(("201"));
    }

    @Test
    public void testGetByStatusGroup_whenInvalidStatusGiven_thenThrowException() {
        Assert.assertThrows(ResponseStatusException.class, () -> {
            httpStatusService.getStatusGroup("201");
        });
    }

    private HttpStatus createHttpStatus(String status) {
        HttpStatus httpStatus = new HttpStatus();
        httpStatus.setStatus(status);
        httpStatus.setImage(new byte[] {});
        return httpStatus;
    }

}
