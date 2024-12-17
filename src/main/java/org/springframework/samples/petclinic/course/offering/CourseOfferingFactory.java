package org.springframework.samples.petclinic.course.offering;

public class CourseOfferingFactory {
    public static CourseOfferingAlgorithm create(String name){
        CourseOfferingAlgorithm result=null;
        switch(name){
            case "ValidCampaingDesignAlgorithm":
                result=new ValidCourseOfferingAlgorithm();
                break;
            case "DummyCourseOfferingAlgorithm":
                result=new DummyCourseOfferingAlgorithm();
                break;
            case "WrongCampaignDesignAlgorithm":
                result=new WrongCourseOfferingAlgorithm();
                break;
            default:
                result=new ValidCourseOfferingAlgorithm();
        }
        return result;
    }
}
