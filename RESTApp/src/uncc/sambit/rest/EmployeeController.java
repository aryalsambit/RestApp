package uncc.sambit.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uncc.sambit.dao.EmployeeDAO;
import uncc.sambit.exception.AppException;
import uncc.sambit.model.Employee;

@Path("/employee")
public class EmployeeController {
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAll(){
		AppResponse resp = new AppResponse();
		try{
			EmployeeDAO eDao = new EmployeeDAO();
			List<Employee> emplist = eDao.getAll();
			resp.setPayload(emplist);
		}catch(AppException ex){
			ex.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(ex.getMessage());
		}
		return resp;
	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getEmployee(@PathParam("id") int empId){
		AppResponse resp = new AppResponse();
		try{
			EmployeeDAO eDao = new EmployeeDAO();
			Employee emp = eDao.getEmployee(empId);
			resp.setPayload(emp);
		}catch(AppException ex){
			ex.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(ex.getMessage());
		}
		return resp;
	}
	
	@GET
	@Path("/del/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse deleteEmployee(@PathParam("id") int empId){
		AppResponse resp = new AppResponse();
		try{
			EmployeeDAO eDao = new EmployeeDAO();
			eDao.deleteEmployee(empId);
			resp.setStatus("Deleted");
			resp.setMessage("Successfully deleted!!");
		}catch(AppException ex){
			ex.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(ex.getMessage());
		}
		return resp;
	}
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse addPerson(Employee emp){
		AppResponse resp = new AppResponse();
		try{
			EmployeeDAO eDao = new EmployeeDAO();
			emp = eDao.addEmployee(emp);
			resp.setMessage("Employee has been added to database");
			resp.setPayload(emp);
		}catch(AppException ex){
			ex.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(ex.getMessage());
		}
		return resp;
	}
}
