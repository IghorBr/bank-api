package com.bank.api.domain;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public abstract class BaseController<T extends BaseDomain, K extends BaseDTO> {

	@Autowired private BaseService<T> baseService;
	private ModelMapper mapper = new ModelMapper();
	private Class<T> entityTarget;
	private Class<K> dtoTarget;
	
	public BaseController(Class<T> entity, Class<K> dto) {
		this.entityTarget = entity;
		this.dtoTarget = dto;
	}

	@GetMapping(value = "")
	public ResponseEntity<List<K>> findAll() {
		List<T> entities = baseService.findAll();
		List<K> dtos = this.mapList(entities);
		
		return ResponseEntity.ok().body(dtos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<K> findById(@PathVariable Long id) {
		T entity = baseService.findById(id).orElseThrow(() -> new RuntimeException("Objeto não encontrado"));
		K dto = mapper.map(entity, dtoTarget);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<Void> saveEntity(@RequestBody @Valid K dto) {
		T entity = mapper.map(dto, entityTarget);
		entity = baseService.save(entity);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> updateEntity(@PathVariable Long id, @RequestBody K dto) {
		T entity = baseService.findById(id).orElseThrow(() -> new RuntimeException("Entidade não encontrada"));
		entity = mapper.map(dto, entityTarget);
		
		entity.setId(id);
		entity = baseService.updateById(entity, id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
		baseService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	protected List<K> mapList(List<T> source) {
	    return source
	    	      .stream()
	    	      .map(element -> mapper.map(element, dtoTarget))
	    	      .collect(Collectors.toList());
	}
	
}
