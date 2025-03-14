package com.yunback.portfolio.domain.repository

import com.yunback.portfolio.domain.constant.SkillType
import com.yunback.portfolio.domain.entity.Project
import com.yunback.portfolio.domain.entity.ProjectDetail
import com.yunback.portfolio.domain.entity.ProjectSkill
import com.yunback.portfolio.domain.entity.Skill
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import kotlin.test.Test

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectRepositoryTest(
    @Autowired val projectRepository: ProjectRepository,
    @Autowired val skillRepository: SkillRepository
) {
    val DATA_SIZE = 2

    private fun createProject(n: Int): Project {
        val project = Project(
            "${n}", "Test Description ${n}",
            2023, 9, 2025, 9, true
        )

        val details = mutableListOf<ProjectDetail>()

        for (i in 1..n) {
            val projectDetail = ProjectDetail("Test ${n}", null, true)
            details.add(projectDetail)
        }
        project.addDeatils(details)

        val skills = skillRepository.findAll()
        val skillsUsedInProject = skills.subList(0, n)
        for (skill in skillsUsedInProject) {
            val projectSkill = ProjectSkill(project, skill)
            project.skills.add(projectSkill)
        }
        return project
    }

    @BeforeAll
    fun beforeAll() {
        println("----- skill 데이터 초기화 시작 -----")
        val skills = mutableListOf<Skill>()
        for (i in 1..DATA_SIZE) {
            val skillTypes = SkillType.values()
            val skill = Skill("Test ${i}", skillTypes[i % skillTypes.size].name, true)
            skills.add(skill)
            println("${skill.name} ${skill.type} 생성됨")
        }
        skillRepository.saveAll(skills)
        println("----- skill 데이터 초기화 종료 -----")

        println("----- 테스트 데이터 초기화 시작 -----")
        val projects = mutableListOf<Project>()
        for (i in 1..DATA_SIZE) {
            val project = createProject(i)
            projects.add(project)
        }
        projectRepository.saveAll(projects)
        println("----- 테스트 데이터 초기화 종료 -----")
    }

    @Test
    fun testFindAll() {
        println("----- findAll 테스트 시작")
        val projects = projectRepository.findAll()
        assertThat(projects).hasSize(DATA_SIZE)
        println("projects.size : ${projects.size}")

        for (project in projects) {
            assertThat(project.details).hasSize(project.name.toInt())
            println("project.details.size : ${project.details.size}")

            assertThat(project.skills).hasSize(project.name.toInt())
            println("project.skills.size : ${project.skills.size}")
        }
        println("----- findAll 테스트 종료")
    }

    @Test
    fun testFindAllByIsActive() {
        println("----- findAll 테스트 시작")
        val projects = projectRepository.findAllByIsActive(true)
        assertThat(projects).hasSize(DATA_SIZE)
        println("projects.size : ${projects.size}")

        for (project in projects) {
            assertThat(project.details).hasSize(project.name.toInt())
            println("project.details.size : ${project.details.size}")

            assertThat(project.skills).hasSize(project.name.toInt())
            println("project.skills.size : ${project.skills.size}")
        }
        println("----- findAll 테스트 종료")
    }
}