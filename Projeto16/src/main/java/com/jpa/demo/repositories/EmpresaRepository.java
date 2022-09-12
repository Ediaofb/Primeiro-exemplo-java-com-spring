package com.jpa.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jpa.demo.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	Empresa findByCnpj(String cnpj);//Busca uma empresa pelo CNPJ
}
