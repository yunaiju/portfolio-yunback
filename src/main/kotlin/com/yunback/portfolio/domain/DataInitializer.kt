package com.yunback.portfolio.domain

import com.yunback.portfolio.domain.constant.SkillType
import com.yunback.portfolio.domain.entity.*
import com.yunback.portfolio.domain.repository.*
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["default"]) // docker일 땐 X
class DataInitializer(
    private val achievementRepository: AchievementRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val skillRepository: SkillRepository,
    private val projectRepository: ProjectRepository,
    private val experienceRepository: ExperienceRepository
) {
    @PostConstruct
    fun initializeData() {
        println("Spring ON, Init Test Data")

        val achievements = mutableListOf<Achievement>(
            Achievement(
                title = "2022 Catkao 해커톤 최우수상",
                description = "설명",
                host = "catkao",
                achievedDate = LocalDate.of(2024, 8, 20),
                isActive = true
            ),
            Achievement(
                title = "정보처리기사",
                description = "설명",
                host = "한국산업인력공단",
                achievedDate = LocalDate.of(2024, 4, 20),
                isActive = true
            )
        )
        achievementRepository.saveAll(achievements)

        val introductions = mutableListOf<Introduction>(
            Introduction(content = "주도적으로 문제를 찾고, 해결하는 고양이입니다.", isActive = true),
            Introduction(content = "기술을 위한 기술이 아닌, 비즈니스 문제를 풀기 위한 기술을 추구합니다.", isActive = true),
            Introduction(content = "기존 소스를 리팩토링하여 더 좋은 구조로 개선하는 작업을 좋아합니다.", isActive = true)
        )
        introductionRepository.saveAll(introductions)

        val links = mutableListOf<Link>(
            Link(name = "GitHub", content = "https://github.com/yunaiju", isActive = true),
            Link(name = "Velog", content = "https://velog.io/@hyejoo04/posts", isActive = true)
        )
        linkRepository.saveAll(links)

        val experience1 = Experience(
            title = "강남대학교",
            description = "ict공학부(소프트웨어전공, 가상현실전공)",
            startYear = 2021,
            startMonth = 3,
            endYear = 2025,
            endMonth = 2,
            isActive = true
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(content = "소프트웨어 연구 학회 활동", isActive = true),
                ExperienceDetail(content = "GPA", isActive = true)
            )
        )
        experienceRepository.saveAll(mutableListOf(experience1))

        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val kotlin = Skill(name = "Kotlin", type = SkillType.LANGUAGE.name, isActive = true)
        val cpp = Skill(name = "C++", type = SkillType.LANGUAGE.name, isActive = true)
        val python = Skill(name = "Python", type = SkillType.LANGUAGE.name, isActive = true)
        val spring = Skill(name = "Spring", type = SkillType.LANGUAGE.name, isActive = true)
        val mariaDB = Skill(name = "MariaDB", type = SkillType.LANGUAGE.name, isActive = true)
        skillRepository.saveAll(mutableListOf(java, kotlin, cpp, python, spring, mariaDB))

        val project1 = Project(
            name = "WebPos",
            description = "일회성 포스기 웹사이트",
            startYear = 2025,
            startMonth = 2,
            endYear = 2025,
            endMonth = 3,
            isActive = true
        )
        project1.addDeatils(
            mutableListOf(
                ProjectDetail(
                    content = "구글 맵스를 활용한 유기묘 발견 지역 정보 제공 API 개발",
                    url = null, isActive = true
                ),
                ProjectDetail(
                    content = "Redis 적용하여 인기 게시글의 조회 속도 1.5초 → 0.5초로 개선",
                    url = null, isActive = true
                )
            )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = java),
                ProjectSkill(project = project1, skill = spring),
                ProjectSkill(project = project1, skill = mariaDB)
            )
        )

        val project2 = Project(
            name = "스키장 정보제공 사이트",
            description = "카메라에서 서버로 전달되는 신호를 분석하여 움직임이 감지될 경우 클라이언트에게 알림 발송 작업.",
            startYear = 2025,
            startMonth = 3,
            endYear = null,
            endMonth = null,
            isActive = true
        )
        project2.addDeatils(
            mutableListOf(
                ProjectDetail(
                    content = "PIL(Pillow) 활용하여 이미지 분석 기능 개발",
                    url = null, isActive = true
                ),
                ProjectDetail(
                    content = "알림 발송을 비동기 처리하여 이미지 분석 - 알림 발송 기능간 의존도 감 소",
                    url = null, isActive = true
                ),
                ProjectDetail(
                    content = "Github Repository",
                    url = "https://github.com/infomuscle", isActive = true
                )
            )
        )
        project2.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project2, skill = java),
                ProjectSkill(project = project2, skill = spring),
                ProjectSkill(project = project2, skill = mariaDB)
            )
        )
        projectRepository.saveAll(mutableListOf(project1, project2))
    }
}