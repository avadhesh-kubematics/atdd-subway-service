package nextstep.subway.path.domain.adapters;

import nextstep.subway.line.application.LineService;
import nextstep.subway.line.domain.Line;
import nextstep.subway.path.domain.SafeSectionInfo;
import nextstep.subway.station.domain.StationFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SafeLineAdapterTest {
    private SafeLineAdapter safeLineAdapter;

    @Mock
    private LineService lineService;

    @BeforeEach
    void setup() {
        safeLineAdapter = new SafeLineAdapter(lineService);
    }

    @DisplayName("모든 지하철 노선의 역 ID를 가져올 수 있다.")
    @Test
    void getAllStationIdsTest() {
        List<Line> mockLines = Arrays.asList(
                new Line("2호선", "초록색", StationFixtures.강남역, StationFixtures.삼성역, 5),
                new Line("3호선", "퍼런색", StationFixtures.역삼역, StationFixtures.잠실역, 5)
        );
        given(lineService.findAllLines()).willReturn(mockLines);

        List<Long> stationIds = safeLineAdapter.getAllStationIds();

        assertThat(stationIds).contains(1L, 2L, 3L, 4L);
    }

    @DisplayName("모든 지하철 노선의 구간 정보를 안전하게 가져올 수 있다.")
    @Test
    void getAllSafeSectionInfosTest() {
        List<Line> mockLines = Arrays.asList(
                new Line("2호선", "초록색", StationFixtures.강남역, StationFixtures.삼성역, 5),
                new Line("3호선", "퍼런색", StationFixtures.역삼역, StationFixtures.잠실역, 5)
        );
        given(lineService.findAllLines()).willReturn(mockLines);

        List<SafeSectionInfo> allSafeSectionInfos = safeLineAdapter.getAllSafeSectionInfos();

        assertThat(allSafeSectionInfos).contains(
                new SafeSectionInfo(1L, 4L, 5),
                new SafeSectionInfo(2L, 3L, 5)
        );
    }
}