public class Algorithm {
	/**
	 * BF匹配算法
	 * 
	 * @param mainStr
	 * @param subStr
	 * @return
	 */
	public static int bf(char[] mainStr, char[] subStr) {
		int mainLen = mainStr.length;
		int subLen = subStr.length;

		int i = 0;
		int j = 0;
		while (i != mainLen && j != subLen) {
			if (mainStr[i] == subStr[j]) {
				i++;
				j++;
			} else {
				i = i - j + 1;
				j = 0;
			}
		}

		if (j == subLen) {
			return i - j + 1;
		} else {
			return -1;
		}
	}

	/**
	 * BM匹配算法
	 * 
	 * @param mainStr
	 * @param subStr
	 * @return
	 */
	public static int bm(char[] mainStr, char[] subStr) {
		// 字符集字符数定义
		// 使其支持汉字
		final int SIZE = 65536;
		int mainLen = mainStr.length;
		int subLen = subStr.length;
		int[] bc = new int[SIZE];
		int[] suffix = new int[subLen];
		boolean[] prefix = new boolean[subLen];
		int i = 0;

		// 预处理模式串
		// 创建模式串的哈希表
		for (i = 0; i < SIZE; ++i) {
			bc[i] = -1;// 初始化哈希表为-1
		}
		// 存储每个字符在子串的位置
		for (i = 0; i < subLen; ++i) {
			bc[subStr[i]] = i;
		}

		// 填充suffix，prefix
		// 两个数组初始化
		for (i = 0; i < subLen; ++i) {
			suffix[i] = -1;
			prefix[i] = false;
		}
		// 公共后缀子串长度(模式串尾部取k个出来，分别比较)
		for (i = 0; i < subLen - 1; ++i) {
			int j = i, len = 0;

			// 与SubStr[0,m-1]求公共后缀子串
			while (j >= 0 && subStr[j] == subStr[subLen - 1 - len]) {
				// 相同后缀子串长度为k时，该子串在SubStr[0,i]中的起始下标
				// (如果有多个相同长度的子串，被赋值覆盖，存较大的)
				j--;
				len++;
				suffix[len] = j + 1;
			}

			// 查找到模式串的头部了
			// 如果公共后缀子串也是模式串的前缀子串
			if (j == -1) {
				prefix[len] = true;
			}
		}

		i = 0;
		int moveLen1 = 0;
		int moveLen2 = 0;
		while (i < mainLen - subLen + 1) {
			// j表示主串与模式串匹配的第一个字符
			int j;

			// 模式串从后往前匹配
			for (j = subLen - 1; j >= 0; --j) {
				if (mainStr[i + j] != subStr[j]) {
					// 坏字符对应模式串中的下标是j
					break;
				}
			}

			// 匹配成功
			if (j < 0) {
				// 返回主串与模式串第一个匹配的字符的位置
				return i + 1;
			}

			// 这里等同于将模式串往后滑动 j - bc[int(a[i+j])] 位
			// 坏字符规则移动距离
			moveLen1 = j - bc[mainStr[i + j]];
			moveLen2 = 0;
			// 如果有好后缀的话
			if (j < subLen - 1) {
				int index = j;
				// 好后缀长度
				int len = subLen - 1 - index;

				// case1，找到跟好后缀一样的模式子串（多个的话，存的靠后的那个（子串起始下标））
				if (suffix[len] != -1) {
					moveLen2 = index - suffix[len] + 1;
				}

				// case2，找不到跟好后缀一样的模式子串，但找得到与好后缀子串一样的模式子串
				for (int k = index + 2; k < subLen; k++) {
					// SubLen - k是好后缀的子串的长度，如果这个好后缀的子串是模式串的前缀子串
					if (prefix[subLen - k]) {
						moveLen2 = k;// 在上面没有找到相同的好后缀下，移动k位，对齐前缀到好后缀
					}
				}

				// case3，都没有匹配的，移动SubLen位（模式串长度）
				moveLen2 = subLen;
			}
			// 取大的移动
			i = i + Math.max(moveLen1, moveLen2);
		}
		return -1;
	}

	/**
	 * KMP匹配算法
	 * 
	 * @param mainStr
	 * @param subStr
	 * @return
	 */
	public static int kmp(char[] mainStr, char[] subStr) {
		int subLen = subStr.length;
		int mainLen = mainStr.length;
		int i = 0;
		int j = 0;

		// temp 数组找值ֵ
		int[] temp = new int[subLen];
		// 初始化
		for (i = 1; i < temp.length; i++) {
			temp[i] = 0;
		}
		temp[0] = -1;

		for (i = 2; i < subLen; ++i) {
			j = i - 1;
			for (int r = 1; j >= 1; --j, ++r) {
				int count = 0;
				for (int k = 0; k < j; ++k) {
					if (subStr[k] == subStr[k + r]) {
						count++;
					}
				}
				if (count == j) {
					temp[i] = count;
					break;
				}
			}
		}

		// KMP核心算法
		i = 0;
		j = 0;
		while (i != mainLen && j != subLen) {
			if (mainStr[i] == subStr[j]) {
				i++;
				j++;
			} else {
				j = temp[j];// j下标回溯至temp[j]储存的位置
			}

			// 如果j下标回溯到了-1值，i、j下标同时加一
			if (j == -1) {
				i++;
				j++;
			}
		}

		// 比对完成，成功则返回开始位置，否则返回0值ֵ
		if (j == subLen) {
			return i - j + 1;
		} else {
			return -1;
		}
	}
}
