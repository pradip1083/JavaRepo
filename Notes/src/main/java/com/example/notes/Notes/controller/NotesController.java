/**
 * 
 */
package com.example.notes.Notes.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Route;
import org.apache.camel.spring.SpringCamelContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.notes.Notes.exception.ResourceNotFoundException;
import com.example.notes.Notes.model.Employee;
import com.example.notes.Notes.model.HelloWorld;
import com.example.notes.Notes.model.JavaCollection;
import com.example.notes.Notes.model.Note;
import com.example.notes.Notes.repository.EmployeeRepository;
import com.example.notes.Notes.repository.NoteRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author prnikam
 *
 */
@RestController
@RequestMapping("/api")
public class NotesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotesController.class);
	@Autowired
	NoteRepository noterepository;

	@Autowired
	EmployeeRepository empRepository;

	@GetMapping("/welcomeToCamel")
	public List sayHello() throws Exception {

		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext-camel.xml");
		CamelContext camelcontext = SpringCamelContext.springCamelContext(appContext, false);
		ProducerTemplate template = camelcontext.createProducerTemplate();
		Map<String, String> map = new HashMap<>();

		map.put("f_name", "Ganesh");
		map.put("l_name", "Nikam");
		map.put("city", "Sangamner");
		map.put("state", "Maharashtra");
		
		Map<String, String> headerValue = new HashMap<>();
		headerValue.put("bb", "Broadband");
		headerValue.put("pstn", "Landline");
		headerValue.put("tv", "BT TV");
		
		
		List<String> result = template.requestBodyAndHeader("direct:broadband", map, "header", headerValue, List.class);
		Route route = camelcontext.getRoute("BROADBAND");
		
		
		String id = route.getId();

		
		HelloWorld obj = (HelloWorld) appContext.getBean("helloWorld");
		System.out.println("Welcome to " + obj.getMessage());
		
		
		// crating new application context
		
		ApplicationContext context_1=new ClassPathXmlApplicationContext("springContext.xml");
		JavaCollection jc=(JavaCollection)context_1.getBean("javaCollection");
		
		System.out.println(jc.getAddressList());
		System.out.println(jc.getAddressSet());
		System.out.println(jc.getAddressMap());
		
		
		
		return result;
	}

	// get all Notes
	@GetMapping("/notes")
	public List<Note> getAllNotes() {
		LOGGER.info("Intered into getAll Notes: ");
		return noterepository.findAll();

	}

	// Save all notes
	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		return noterepository.save(note);
	}

	// get particular note
	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
		return noterepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("id", noteId, "notes"));
	}

	// update note
	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note notedetails) {

		Note note = noterepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("id", noteId, "notes"));

		note.setTitle(notedetails.getTitle());
		note.setContent(notedetails.getContent());
		Note updatedNote = noterepository.save(note);
		return updatedNote;

	}

	// delete note
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
		Note note = noterepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("id", noteId, "notes"));

		noterepository.delete(note);
		return ResponseEntity.ok().build();
	}

	// Add Employee
	@PostMapping("/employee")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return empRepository.save(emp);
	}

	/*
	 * // Add address
	 * 
	 * @PostMapping("/address") public Address updateAddress(@Valid @RequestBody
	 * Address emp) { return addRepository.save(emp); }
	 */

	@GetMapping("/getEmployee")
	public List<Employee> getEmployee() {
		return empRepository.findAll();
	}

	@PostMapping("/saveAllData")
	public void SaveAllData(@Valid @RequestBody String data)
			throws JsonParseException, JsonMappingException, IOException {

		JSONObject obj = new JSONObject(data);
		Employee emp = new Employee();
		Set<Note> notes = new HashSet<Note>();

		emp.setF_name(obj.getString("f_name"));
		emp.setL_name(obj.getString("l_name"));
		JSONArray noteArray = (JSONArray) obj.get("note");

		ObjectMapper mapper = new ObjectMapper();
		Note note = null;
		for (int i = 0; i < noteArray.length(); i++) {
			obj = noteArray.getJSONObject(i);

			note = mapper.readValue(obj.toString(), Note.class);
			note.setEmp(emp);
			notes.add(note);

		}

		emp.setNote(notes);

		empRepository.save(emp);

	}

	@GetMapping("/getEmployeeData/{id}")
	@JsonIgnore
	public Employee getEmpData(@PathVariable(value = "id") Long id) {

		Employee emp = empRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("id", id, "Employee"));
		emp.getNote();
		return emp;

	}

	@GetMapping("/sample")
	public @ResponseBody String getdata(@RequestBody String form) {

		JSONObject obj = new JSONObject(form);

		System.out.println(obj.get("obj1"));
		System.out.println(obj.get("obj2"));

		return obj.get("obj1").toString() + "\n" + obj.get("obj2").toString();
	}

	@GetMapping("/secured")
	public String secured() {
		System.out.println("Inside Secured API...");
		return "Hello Pradip" + new Date();
	}

	@GetMapping("/findByName/f_name/{name}")
	public String getEmpDetail(@PathVariable(value = "name") String name) {
		String lastName = empRepository.findEmpByName(name);

		return lastName;
	}

}
