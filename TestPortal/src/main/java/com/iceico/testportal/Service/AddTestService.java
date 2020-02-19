/**
 * 
 */
package com.iceico.testportal.Service;

import java.util.List;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.AddTest;

/**
 * @author puja
 *
 */
public interface AddTestService {

	public void saveAddTest(AddTest addTest);

	public void deleteAddTest(Long addTestId);

	public List<AddTest> getAddTestList();

	public AddTest getAddTestById(Long addTestId) throws ResourceNotFoundException;

}
