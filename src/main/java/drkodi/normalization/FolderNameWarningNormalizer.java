package drkodi.normalization;

import drkodi.ProtoTypeNormalizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class FolderNameWarningNormalizer extends ProtoTypeNormalizer {

    private final FolderNameCompareNormalizerConfiguration configuration;

    @Override
    protected ProtoTypeNormalizerConfiguration getConfiguration() {
        return configuration;
    }

    public String normalize(String movieTitle){
        var result = super.normalize(movieTitle);
        log.debug("Normalized from {} to {}", movieTitle, result);
        return result;
    }


}
