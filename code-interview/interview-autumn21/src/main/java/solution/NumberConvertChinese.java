package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * 把十亿以内的整数转为中文，比如123456转为十二万三千四百五十六
 *
 * @author:edgarding
 * @date:2021/6/22
 **/
public class NumberConvertChinese {
    /**
     * 进位
     */
    private static final int UNIT_STEP = 4;

    private static final String[] CN_UNIT = {
        "个", "十", "百", "千", "万", "十",
        "百", "千", "亿", "十", "百", "千", "万"
    };

    private static final String[] CN_CHAR = {
        "零", "一", "二", "三", "四",
        "五", "六", "七", "八", "九"
    };

    public String cvt(int num) {
        StringBuilder sb = new StringBuilder();
        String[] strings = convert(num);
        for (String ch : strings) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public String[] convert(int num) {
        if (num < 10) {
            return new String[] {CN_CHAR[num]};
        }
        char[] nums = String.valueOf(num).toCharArray();
        // 超出表示范围
        if (nums.length > CN_UNIT.length) {
            return null;
        }
        List<String> cnChars = new ArrayList<>();
        // 上一个是否进位
        boolean isLastUnitStep = false;
        for (int pos = nums.length - 1; pos >= 0; pos--) {
            // 当前数字
            char ch = nums[pos];
            // 当前中文数字
            String cnChar = CN_CHAR[ch - '0'];
            // 当前单位下标
            int unitPos = nums.length - 1 - pos;
            // 当前单位
            String cnUnit = CN_UNIT[unitPos];
            // 是否为0
            boolean isZero = (ch == '0');
            // 是否为低位0
            // 例如 20 二十零
            boolean isLowZero = (pos + 1 < nums.length && nums[pos + 1] == '0');
            // 是否需要单位进位
            boolean isUnitStep = (unitPos >= UNIT_STEP && (unitPos % UNIT_STEP) == 0);

            // 去除相邻的上一个单位进位
            if (isUnitStep && isLastUnitStep) {
                int size = cnChars.size();
                cnChars.remove(size - 1);
                // 补0
                if (!CN_CHAR[0].equals(cnChars.get(size - 2))) {
                    cnChars.add(CN_CHAR[0]);
                }
            }
            // 单位进位(万、亿)，或者非0时加上单位
            if (isUnitStep || !isZero) {
                cnChars.add(cnUnit);
                isLastUnitStep = isUnitStep;
            }
            // 当前位为0低位为0，或者当前位为0并且为单位进位时进行省略
            if (isZero && (isLowZero || isUnitStep)) {
                continue;
            }
            cnChars.add(cnChar);
            isLastUnitStep = false;
        }
        Collections.reverse(cnChars);
        // 清除最后一位的0
        int chSize = cnChars.size();
        String chEnd = cnChars.get(chSize - 1);
        if (CN_CHAR[0].equals(chEnd) || CN_UNIT[0].equals(chEnd)) {
            cnChars.remove(chSize - 1);
        }
        return cnChars.toArray(new String[] {});
    }
}
