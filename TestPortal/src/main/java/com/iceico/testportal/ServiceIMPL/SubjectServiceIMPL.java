package com.iceico.testportal.ServiceIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iceico.testportal.Exceptions.ResourceNotFoundException;
import com.iceico.testportal.Model.Subject;
import com.iceico.testportal.Repository.SubjectRepository;
import com.iceico.testportal.Service.SubjectService;

/**
 * @author puja
 *
 */

@Service
@Transactional
public class SubjectServiceIMPL implements SubjectService {

	/**
	 * 
	 */
	public SubjectServiceIMPL() {

	}

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public void saveSubject(Subject subject) {
		this.subjectRepository.save(subject);
	}

	@Override
	public void deleteSubject(Long subjectId) {
		this.subjectRepository.deleteById(subjectId);
	}

	@Override
	public List<Subject> getSubjectList() {
		return this.subjectRepository.findAll();
	}

	@Override
	public Subject getSubjectById(Long subjectId) throws ResourceNotFoundException {
		return this.subjectRepository.findById(subjectId)
				.orElseThrow(() -> new ResourceNotFoundException("Subject Id not found" + subjectId));
	}

}
