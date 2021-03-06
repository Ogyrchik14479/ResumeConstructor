package igor.reznikov.resume.constructor.servecies.impl;

import igor.reznikov.resume.constructor.dtos.request.ResumePost;
import igor.reznikov.resume.constructor.dtos.response.ResumeView;
import igor.reznikov.resume.constructor.entities.Resume;
import igor.reznikov.resume.constructor.mappers.ResumeMapper;
import igor.reznikov.resume.constructor.repositories.ResumeRepository;
import igor.reznikov.resume.constructor.servecies.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    private final ResumeMapper resumeMapper;

    @Override
    public Long add(ResumePost resumePost) {

        Long resumeId = save(new Resume()).getId();

        resumePost.setId(resumeId);
        resumePost.getBasicInformation().setResumeId(resumeId);
        resumePost.getPersonalInformation().setResumeId(resumeId);
        resumePost.getWorkExperienceList().forEach(item -> item.setResumeId(resumeId));
        resumePost.getEducationList().forEach(item -> item.setResumeId(resumeId));
        resumePost.getCourseList().forEach(item -> item.setResumeId(resumeId));
        resumePost.getAchievementList().forEach(item -> item.setResumeId(resumeId));
        resumePost.getPublicationList().forEach(item -> item.setResumeId(resumeId));
        resumePost.getSkillList().forEach(item -> item.setResumeId(resumeId));
        resumePost.getLanguageList().forEach(item -> item.setResumeId(resumeId));
        resumePost.getHobbyList().forEach(item -> item.setResumeId(resumeId));
        resumePost.getCourseList().forEach(item -> item.setResumeId(resumeId));
        resumePost.getCustomizableSectionList().forEach(item -> item.setResumeId(resumeId));
        resumePost.getRecommendationList().forEach(item -> item.setResumeId(resumeId));

        Resume resume = resumeMapper.toResume(resumePost);
        return resumeRepository.save(resume).getId();
    }

    private Resume save(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Override
    public ResumeView getResumeById(Long id) {
        return resumeMapper.toResumeDTO(resumeRepository.findById(id).get());
    }
}
