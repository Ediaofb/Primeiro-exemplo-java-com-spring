package com.jpa.demo;

import com.jpa.demo.entities.Empresa;
import com.jpa.demo.repositories.EmpresaRepository;

import java.util.List;
import org.aspectj.weaver.patterns.ArgsAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Projeto16Application {

	@Autowired //Injeta um componente dentro de outro componente 
	private EmpresaRepository empresaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Projeto16Application.class, args);
	}
	
	@Bean //serve para exportar uma classe para o Spring, para que ele consiga carregar essa classe e fazer injeção de dependência dela em outra classes.
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Empresa empresa = new Empresa();
			empresa.setRazaoSocial("Edson IT");
			empresa.setCnpj("74646215000104");
			
			this.empresaRepository.save(empresa);
			
			List<Empresa> empresas = empresaRepository.findAll();//Me retorne todas as empresas do banco
			empresas.forEach(System.out::println); //forEach para iterar na lista e imprimir na tela todos os valores das empresas
			
			Empresa empresaDb = empresaRepository.findOne(1L);
			System.out.println("Empresa por ID: " + empresaDb);
			
			empresaDb.setRazaoSocial("Edson IT Web");
			this.empresaRepository.save(empresaDb);
			
			Empresa empresaCnpj = empresaRepository.findByCnpj("74646215000104");
			System.out.println("Empresa por CNPJ: " + empresaCnpj);
			
			this.empresaRepository.deleteAll();
			empresas = empresaRepository.findAll();
			System.out.println("Empresas: " + empresas.size());
			
		};
	}
}
