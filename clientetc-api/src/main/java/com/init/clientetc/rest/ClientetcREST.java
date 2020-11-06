package com.init.clientetc.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.clientetc.dao.ClientetcDAO;
import com.init.clientetc.entidad.Cliente;

@RestController
@RequestMapping("clientetc")
public class ClientetcREST {
	
		@Autowired
		private ClientetcDAO clientetcDAO;
	
		@GetMapping
		public ResponseEntity<List<Cliente>> getClientes(){
			List<Cliente> clientes = clientetcDAO.findAll();
			return ResponseEntity.ok(clientes);
		}
		
		@RequestMapping(value="{clientetcId}")
		public ResponseEntity<Cliente> getClienteById(@PathVariable("clientetcId") Long clientetcId){
			
			Optional<Cliente> optionalCliente = clientetcDAO.findById(clientetcId);
			
			if(optionalCliente.isPresent()){
				return ResponseEntity.ok(optionalCliente.get());
			}else {
				return ResponseEntity.noContent().build();
			}
		}
		
		@PostMapping
		public ResponseEntity<Cliente> createClientetc(@RequestBody Cliente cliente){
			Cliente newCliente = clientetcDAO.save(cliente);
			return ResponseEntity.ok(newCliente);
		}
		
		@DeleteMapping(value="{clientetcId}")
		public ResponseEntity<Void> deleteClientetc(@PathVariable("clientetcId") Long clientetcId){
			clientetcDAO.deleteById(clientetcId);
			return ResponseEntity.ok(null);
		}
		
		@PutMapping(value="{clientetcId}")
		public ResponseEntity<Cliente> updateClientetc(@RequestBody Cliente cliente){
			Optional<Cliente> optionalCliente = clientetcDAO.findById(cliente.getId());
			
			if(optionalCliente.isPresent()){
				Cliente updateCliente = optionalCliente.get();
				updateCliente.setNombre(cliente.getNombre());
				clientetcDAO.save(updateCliente);
				return ResponseEntity.ok(updateCliente);
			}else {
				return ResponseEntity.notFound().build();
			}
		}
}
