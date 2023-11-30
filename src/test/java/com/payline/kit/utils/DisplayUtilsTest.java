package com.payline.kit.utils;

import com.payline.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DisplayUtilsTest {

    @Test
    void objectDetails() {

        final List<String> list = List.of("toto");

        final String result = DisplayUtils.objectDetails(list);
        assertNotNull(result);
        assertEquals("<ul><li class=\"object\">java.util.ImmutableCollections$List12</li><li>java.lang.String => toto</li><li>java.lang.String => toto</li> </ul> </ul> </ul>",
                result);
    }
}