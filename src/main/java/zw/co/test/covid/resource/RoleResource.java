package zw.co.test.covid.resource;


import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.test.covid.model.Role;
import zw.co.test.covid.service.RoleService;

import java.util.Optional;

@RestController
@RequestMapping("api/role")
@AllArgsConstructor
public class RoleResource {

    private RoleService roleService;


    @PostMapping("/")
    public ResponseEntity create(@RequestBody Role role) {

        return ResponseEntity
                .status(201)
                .body(roleService.save(role));

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Role role, @PathVariable String id) {
        Optional<Role> optionalRole = Optional.of(roleService.findOne(id).get());
             optionalRole.orElseThrow(() -> new ResourceNotFoundException(""));
             optionalRole.get().setRoleName(role.getRoleName());

        return ResponseEntity.ok(roleService.save(optionalRole.get()));
    }

    @GetMapping("/")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable String id){
        Role role = roleService.findOne(id)
                .orElseThrow(()-> new ResourceNotFoundException("Role not found"));
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
        roleService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
