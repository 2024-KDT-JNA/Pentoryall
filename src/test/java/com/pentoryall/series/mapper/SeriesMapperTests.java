package com.pentoryall.series.mapper;


import com.pentoryall.PentoryallApplication;
import com.pentoryall.comment.dto.CommentDetailDTO;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.user.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@ContextConfiguration(classes = { PentoryallApplication.class })
public class SeriesMapperTests {
    @Autowired
    private SeriesMapper seriesMapper;

    @Test
    @DisplayName("새로운 시리즈 추가 테스트")
    @Transactional
    public void testAddSeriesOptions(){
        SeriesDTO series = new SeriesDTO();
        series.setUserCode(1L);
        series.setTitle("시리즈 등록 테스트 제목");
        series.setDescription("시리즈 등록 테스트 내용요약");
        series.setThumbnailImage("시리즈 등록 테스트 썸네일 경로");
        series.setIsMembershipOnly('Y');

        assertDoesNotThrow(() -> seriesMapper.addSeriesOptions(series));
    }

    @Test
    @DisplayName("시리즈 업데이트 테스트")
    @Transactional
    public void testUpdateSeries() {
        SeriesDTO series = new SeriesDTO();
        series.setUserCode(1L);
        series.setTitle("시리즈 등록 테스트 제목");
        series.setDescription("시리즈 등록 테스트 내용요약");
        series.setThumbnailImage("시리즈 등록 테스트 썸네일 경로");
        series.setIsMembershipOnly('Y');

        seriesMapper.addSeriesOptions(series);

        SeriesDTO recentSeries = seriesMapper.selectRecentSeriesCode();

        series.setCode(recentSeries.getCode());
        series.setTitle("업데이트 후 제목");
        series.setIsMembershipOnly('N');

        seriesMapper.updateSeries(series,recentSeries.getCode());

        SeriesDTO updatedSeries = seriesMapper.selectRecentSeriesCode();

        assertEquals("업데이트 후 제목", updatedSeries.getTitle());
        assertEquals('N', updatedSeries.getIsMembershipOnly());
    }

    @Test
    @DisplayName("시리즈 조회 테스트")
    @Transactional
    public void testSelectSeriesByTitle() {
        SeriesDTO series = new SeriesDTO();
        series.setUserCode(1L);
        series.setTitle("시리즈 조회 테스트 제목");
        series.setDescription("시리즈 조회 테스트 내용요약");
        series.setThumbnailImage("시리즈 조회 테스트 썸네일 경로");
        series.setIsMembershipOnly('Y');

        seriesMapper.addSeriesOptions(series);

        SeriesDTO recentSeries = seriesMapper.selectRecentSeriesCode();

        SeriesDTO actualSeries = seriesMapper.selectSeriesByTitle(recentSeries.getCode());

        assertEquals("시리즈 조회 테스트 제목", actualSeries.getTitle());
        assertEquals('Y', actualSeries.getIsMembershipOnly());
    }

    @Test
    @DisplayName("시리즈 삭제 테스트")
    @Transactional
    public void testRemoveReply() {
        SeriesDTO series = new SeriesDTO();
        series.setUserCode(1L);
        series.setTitle("시리즈 삭제 테스트 제목");
        series.setDescription("시리즈 삭제 테스트 내용요약");
        series.setThumbnailImage("시리즈 삭제 테스트 썸네일 경로");
        series.setIsMembershipOnly('Y');

        seriesMapper.addSeriesOptions(series);

        SeriesDTO seriesDTO = seriesMapper.selectRecentSeriesCode();
        System.out.println("seriesDTO = " + seriesDTO);
        seriesMapper.deleteSeries(seriesDTO.getCode());

        // Then: 삭제된 댓글의 상태 확인
        SeriesDTO deletedSeries = seriesMapper.selectSeriesByTitle(seriesDTO.getCode());
        assertNull(deletedSeries);
    }


}
