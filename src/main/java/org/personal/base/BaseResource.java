package org.personal.base;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BaseResource<E extends BaseEntity, D extends BaseDto> {

    static final String BASE_URL = "/api";

    private final BaseService<E, D> baseService;

    @Get
    public HttpResponse<List<D>> findAll() {
        return HttpResponse.ok(baseService.findAll());
    }

    @Post
    public HttpResponse<D> create(@Body D dto) {
        return HttpResponse.created(baseService.save(dto));
    }

    @Put("/{id}")
    public HttpResponse<D> update(@PathVariable Long id, @Body D dto) {
        if (dto.getId() == null || !dto.getId().equals(id)) {
            return HttpResponse.badRequest();
        }
        return HttpResponse.ok(baseService.update(dto));
    }

    @Delete("/{id}")
    public HttpResponse<Void> remove(@PathVariable Long id) {
        D dto = baseService.findById(id);
        baseService.delete(dto);
        return HttpResponse.noContent();
    }

    @Get("/{id}")
    public HttpResponse<D> findOne(@PathVariable Long id) {
        return HttpResponse.ok(baseService.findById(id));
    }
}
