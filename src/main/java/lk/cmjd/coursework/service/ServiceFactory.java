package lk.cmjd.coursework.service;

import lk.cmjd.coursework.dto.StudentDto;
import lk.cmjd.coursework.service.custom.Impl.*;
import lk.cmjd.coursework.service.custom.StudentService;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    
    private ServiceFactory(){
        
    }
    
    public static ServiceFactory getInstance(){
        if(serviceFactory == null){
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }
    
    public SuperService getService(ServiceType type){
        switch (type) {
            case STUDENT:
                return new StudentserviceImpl();
            case COURSE:
                return new CourseServiceImpl();
            case ENROLLMENT:
                return new EnrollmentServiceImpl();
            case LOGIN:
                return new LoginServiceImpl();
            case DEPARTMENT:
                return new DepartmentServiceImpl();
            case PREREQUISITE:
                 return new PreRequisiteServiceImpl();
            case USER:
                  return new UserServiceImpl();
            default:
                return null;
        }
    }
    
    public enum ServiceType {
        STUDENT, COURSE, ENROLLMENT,DEPARTMENT,LOGIN,PREREQUISITE,USER
    }
}
