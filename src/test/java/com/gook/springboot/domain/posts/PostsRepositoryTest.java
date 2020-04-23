package com.gook.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    
    @Autowired
    PostsRepository postsRepository;
    
    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }
    
    @Test
    public void 게시글저장_불러오기() throws Exception {
        //given
        String title = "테스트 게시글";
        String content = "테스트본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("ehdaudrnr3@daum.net")
                .build()
        );

        //when
        List<Posts> postList = postsRepository.findAll();

        //then

        Posts posts = postList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.of(2019,02, 23, 4, 40);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        //when
        List<Posts> all = postsRepository.findAll();

        //then
        Posts posts = all.get(0);
        System.out.println("posts = createDate: " + posts.getCreateDate()+", modifiedDate:"+posts.getModifiedDate());
        System.out.println("Format = " + now.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        boolean isWeekend = now.query(temporal->{
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
        });

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
        assertThat(now.getDayOfWeek()).isEqualTo(DayOfWeek.SATURDAY);
        assertThat(isWeekend).isTrue();
    }

}