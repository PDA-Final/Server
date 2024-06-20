-- Fund product Sql
INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (513, "미래에셋TIGER필라델피아반도체레버리지증권상장지수투자신탁(주식혼합-파생형)(합성)", 3, 60, "['ETF(해외주식), 레버리지, 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (513, "K55301DS3146", 26382.79, 291.54, 2266.28, 26.77, 12.45, 100.88, 62.02, 115.56, null, null, 0.67, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "정보기술섹터", '이 투자신탁은 주식관련 파생상품 및 집합투자증권을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하며, 미국 주식으로 구성된 PHLX Semiconductor Sector 지수(원화환산)를 기초지수로하여 1좌당 순자산가치의 일간변 동률을 기초지수 일간변동률의 양의 2배수로 연동하여 투자신탁재산을 운용함을 목적으로 합니다.', '이 투자신탁은 Nasdaq에서 발표하는 PHLX Semiconductor Sector 지수(원화환산)를 기초지수로 하여 기초지수의 일간 수익률의 양의 2배수 수익률과 연동하는 것을 목적으로 하는 상장지수투자신탁으로서, 기초지수관련 파생상품(스왑) 및 집합투자증권을 주된 투자대상으로 하여 투자신탁의 일간수익률이 기초지수 일간수익률의 양의 2배수 수익률과 연동하도록 운용합니다.', '북미', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (514, "미래에셋TIGER미국나스닥100레버리지증권상장지수투자신탁(주식혼합-파생형)(합성)", 3, 60, "['ETF(해외주식), 레버리지, 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (514, "K55301DQ3577", 19057.81, 127.56, 657.49, 14.35, 8.72, 46.63, 35.38, 73.37, null, null, 0.37, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "북미주식", '이 투자신탁은 주식관련 파생상품 및 집합투자증권을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하며, 미국 주식으로 구성된 &#34;NASDAQ-100 지수(원화환산)&#34;를 기초지수로하여 1좌당 순자산가치의 일간변동률을 기초지 수 일간변동률의 양의 2배수로 연동하여 투자신탁재산을 운용함을 목적으로 합니다.', '이 투자신탁은 Nasdaq에서 발표하는 &#34;NASDAQ-100 지수(원화환산)&#34;를 기초지수로 하여 기초지수의 일간수익률의 양(陽) 의 2배수 수익률과 연동하는 것을 목적으로 하는 상장지수투자신탁으로서, 기초지수관련 파생상품(스왑 및 선물) 및 집합 투자증권을 주된 투자대상으로 하여 투자신탁의 일간수익률이 기초지수 일간수익률의 양의 2배수 수익률과 연동하도 록 운용합니다.', '북미', "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (515, "미래에셋인디아인프라섹터증권모투자신탁(주식)", 3, 60, "['인프라펀드, 모펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (515, "KR5301714576", 2775.85, 115.74, 1162.7, 10.67, 20.02, 47.72, 37.01, 70.98, 117.13, 154.86, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", '인도의 인프라관련 주식에 주로 투자하여 장기적인 자본이득을추구합니다주로 주식에 투자하는 주식형펀드로서 인디아의 인프라관련 주식에 투자하여 장기적인 자본이득을 추구합니다.', '자산총액의 60% 이상을 인도에 상장된 인프라 관련 주식에 투자합니다. 인프라 관련 주식이라 함은 사회기반시설과 관련된 산업재, 원자재, 에너지, 유틸리티, 통신 등 업종에 속한 주식을 뜻합니다. 벤치마크인 MSCI India Infra Index (Customized)의 수익률을 상회하는 투자 수익을 거두는 것을 목표로 합니다.', '인도', "상승", "좋음", "보통", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (516, "미래에셋연금인디아인프라증권자투자신탁 1(주식)", 3, 60, "['인프라펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (516, "K55301B25311", 1130.22, 46.91, 858.49, 10.61, 19.93, 47.54, 36.88, 70.68, 116.68, 154.21, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도 주식에 주로 투자하는 미래에셋인디아인프라섹터증권모투자신탁(주식)을 법 시행령 제94조제2항제4호에서 규정하는주된 투자대상자산으로 하여 투자대상자산의 가격 상승에 따른 투자 수익을 추구합니다.', '① 기본적으로 미래에셋인디아인프라섹터증권모투자신탁(주식)에 90% 이상 투자합니다. ② 모투자신탁에의 투자비중은 시장상황에 따라 전략적으로 결정됩니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (517, "미래에셋인디아인프라섹터증권자투자신탁 1(주식)", 3, 60, "['인프라펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (517, "KR5301714584", 3060.99, 127.36, 302.03, 10.65, 19.96, 47.5, 36.87, 70.61, 116.51, 153.83, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도 지역 주식에 주로 투자하는 모투자신탁을 법 시행령 제94조 제2항 제4호에서 규정하는 주된 투자대상자산으로 하여 투자대상자산의 가격 상승에 따른 투자 수익을 추구합니다.', '이 투자신탁이 투자하는 미래에셋 인디아 인프라 섹터 증권 모투자신탁(주식)의 투자전략은 다음과 같습니다. 1. 인도의 인프라관련 주식에 60% 이상 투자합니다. 2. 주식은 개별 기업의 가치 및 위험 등에 대한 내재적 가치 분석에 의한 운용 전략과 경제 환경 등에 대한 거시 경제 분석에 의한 운용 전략을 병행하여 적극적으로 운용합니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (518, "미래에셋TIGER필라델피아반도체레버리지증권상장지수투자신탁(주식혼합-파생형)(합성)", 3, 60, "['ETF(해외주식), 레버리지, 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (518, "K55301DS3146", 26382.79, 291.54, 2266.28, 26.77, 12.45, 100.88, 62.02, 115.56, null, null, 0.67, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "정보기술섹터", '이 투자신탁은 주식관련 파생상품 및 집합투자증권을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하며, 미국 주식으로 구성된 PHLX Semiconductor Sector 지수(원화환산)를 기초지수로하여 1좌당 순자산가치의 일간변 동률을 기초지수 일간변동률의 양의 2배수로 연동하여 투자신탁재산을 운용함을 목적으로 합니다.', '이 투자신탁은 Nasdaq에서 발표하는 PHLX Semiconductor Sector 지수(원화환산)를 기초지수로 하여 기초지수의 일간 수익률의 양의 2배수 수익률과 연동하는 것을 목적으로 하는 상장지수투자신탁으로서, 기초지수관련 파생상품(스왑) 및 집합투자증권을 주된 투자대상으로 하여 투자신탁의 일간수익률이 기초지수 일간수익률의 양의 2배수 수익률과 연동하도록 운용합니다.', '북미', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (519, "미래에셋TIGER미국나스닥100레버리지증권상장지수투자신탁(주식혼합-파생형)(합성)", 3, 60, "['ETF(해외주식), 레버리지, 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (519, "K55301DQ3577", 19057.81, 127.56, 657.49, 14.35, 8.72, 46.63, 35.38, 73.37, null, null, 0.37, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "북미주식", '이 투자신탁은 주식관련 파생상품 및 집합투자증권을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하며, 미국 주식으로 구성된 &#34;NASDAQ-100 지수(원화환산)&#34;를 기초지수로하여 1좌당 순자산가치의 일간변동률을 기초지 수 일간변동률의 양의 2배수로 연동하여 투자신탁재산을 운용함을 목적으로 합니다.', '이 투자신탁은 Nasdaq에서 발표하는 &#34;NASDAQ-100 지수(원화환산)&#34;를 기초지수로 하여 기초지수의 일간수익률의 양(陽) 의 2배수 수익률과 연동하는 것을 목적으로 하는 상장지수투자신탁으로서, 기초지수관련 파생상품(스왑 및 선물) 및 집합 투자증권을 주된 투자대상으로 하여 투자신탁의 일간수익률이 기초지수 일간수익률의 양의 2배수 수익률과 연동하도 록 운용합니다.', '북미', "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (520, "미래에셋인디아인프라섹터증권모투자신탁(주식)", 3, 60, "['인프라펀드, 모펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (520, "KR5301714576", 2775.85, 115.74, 1162.7, 10.67, 20.02, 47.72, 37.01, 70.98, 117.13, 154.86, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", '인도의 인프라관련 주식에 주로 투자하여 장기적인 자본이득을추구합니다주로 주식에 투자하는 주식형펀드로서 인디아의 인프라관련 주식에 투자하여 장기적인 자본이득을 추구합니다.', '자산총액의 60% 이상을 인도에 상장된 인프라 관련 주식에 투자합니다. 인프라 관련 주식이라 함은 사회기반시설과 관련된 산업재, 원자재, 에너지, 유틸리티, 통신 등 업종에 속한 주식을 뜻합니다. 벤치마크인 MSCI India Infra Index (Customized)의 수익률을 상회하는 투자 수익을 거두는 것을 목표로 합니다.', '인도', "상승", "좋음", "보통", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (521, "미래에셋연금인디아인프라증권자투자신탁 1(주식)", 3, 60, "['인프라펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (521, "K55301B25311", 1130.22, 46.91, 858.49, 10.61, 19.93, 47.54, 36.88, 70.68, 116.68, 154.21, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도 주식에 주로 투자하는 미래에셋인디아인프라섹터증권모투자신탁(주식)을 법 시행령 제94조제2항제4호에서 규정하는주된 투자대상자산으로 하여 투자대상자산의 가격 상승에 따른 투자 수익을 추구합니다.', '① 기본적으로 미래에셋인디아인프라섹터증권모투자신탁(주식)에 90% 이상 투자합니다. ② 모투자신탁에의 투자비중은 시장상황에 따라 전략적으로 결정됩니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (522, "미래에셋인디아인프라섹터증권자투자신탁 1(주식)", 3, 60, "['인프라펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (522, "KR5301714584", 3060.99, 127.36, 302.03, 10.65, 19.96, 47.5, 36.87, 70.61, 116.51, 153.83, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도 지역 주식에 주로 투자하는 모투자신탁을 법 시행령 제94조 제2항 제4호에서 규정하는 주된 투자대상자산으로 하여 투자대상자산의 가격 상승에 따른 투자 수익을 추구합니다.', '이 투자신탁이 투자하는 미래에셋 인디아 인프라 섹터 증권 모투자신탁(주식)의 투자전략은 다음과 같습니다. 1. 인도의 인프라관련 주식에 60% 이상 투자합니다. 2. 주식은 개별 기업의 가치 및 위험 등에 대한 내재적 가치 분석에 의한 운용 전략과 경제 환경 등에 대한 거시 경제 분석에 의한 운용 전략을 병행하여 적극적으로 운용합니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (523, "미래에셋TIGER필라델피아반도체레버리지증권상장지수투자신탁(주식혼합-파생형)(합성)", 3, 60, "['ETF(해외주식), 레버리지, 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (523, "K55301DS3146", 26382.79, 291.54, 2266.28, 26.77, 12.45, 100.88, 62.02, 115.56, null, null, 0.67, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "정보기술섹터", '이 투자신탁은 주식관련 파생상품 및 집합투자증권을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하며, 미국 주식으로 구성된 PHLX Semiconductor Sector 지수(원화환산)를 기초지수로하여 1좌당 순자산가치의 일간변 동률을 기초지수 일간변동률의 양의 2배수로 연동하여 투자신탁재산을 운용함을 목적으로 합니다.', '이 투자신탁은 Nasdaq에서 발표하는 PHLX Semiconductor Sector 지수(원화환산)를 기초지수로 하여 기초지수의 일간 수익률의 양의 2배수 수익률과 연동하는 것을 목적으로 하는 상장지수투자신탁으로서, 기초지수관련 파생상품(스왑) 및 집합투자증권을 주된 투자대상으로 하여 투자신탁의 일간수익률이 기초지수 일간수익률의 양의 2배수 수익률과 연동하도록 운용합니다.', '북미', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (524, "미래에셋TIGER미국나스닥100레버리지증권상장지수투자신탁(주식혼합-파생형)(합성)", 3, 60, "['ETF(해외주식), 레버리지, 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (524, "K55301DQ3577", 19057.81, 127.56, 657.49, 14.35, 8.72, 46.63, 35.38, 73.37, null, null, 0.37, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "북미주식", '이 투자신탁은 주식관련 파생상품 및 집합투자증권을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하며, 미국 주식으로 구성된 &#34;NASDAQ-100 지수(원화환산)&#34;를 기초지수로하여 1좌당 순자산가치의 일간변동률을 기초지 수 일간변동률의 양의 2배수로 연동하여 투자신탁재산을 운용함을 목적으로 합니다.', '이 투자신탁은 Nasdaq에서 발표하는 &#34;NASDAQ-100 지수(원화환산)&#34;를 기초지수로 하여 기초지수의 일간수익률의 양(陽) 의 2배수 수익률과 연동하는 것을 목적으로 하는 상장지수투자신탁으로서, 기초지수관련 파생상품(스왑 및 선물) 및 집합 투자증권을 주된 투자대상으로 하여 투자신탁의 일간수익률이 기초지수 일간수익률의 양의 2배수 수익률과 연동하도 록 운용합니다.', '북미', "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (525, "미래에셋인디아인프라섹터증권모투자신탁(주식)", 3, 60, "['인프라펀드, 모펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (525, "KR5301714576", 2775.85, 115.74, 1162.7, 10.67, 20.02, 47.72, 37.01, 70.98, 117.13, 154.86, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", '인도의 인프라관련 주식에 주로 투자하여 장기적인 자본이득을추구합니다주로 주식에 투자하는 주식형펀드로서 인디아의 인프라관련 주식에 투자하여 장기적인 자본이득을 추구합니다.', '자산총액의 60% 이상을 인도에 상장된 인프라 관련 주식에 투자합니다. 인프라 관련 주식이라 함은 사회기반시설과 관련된 산업재, 원자재, 에너지, 유틸리티, 통신 등 업종에 속한 주식을 뜻합니다. 벤치마크인 MSCI India Infra Index (Customized)의 수익률을 상회하는 투자 수익을 거두는 것을 목표로 합니다.', '인도', "상승", "좋음", "보통", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (526, "미래에셋연금인디아인프라증권자투자신탁 1(주식)", 3, 60, "['인프라펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (526, "K55301B25311", 1130.22, 46.91, 858.49, 10.61, 19.93, 47.54, 36.88, 70.68, 116.68, 154.21, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도 주식에 주로 투자하는 미래에셋인디아인프라섹터증권모투자신탁(주식)을 법 시행령 제94조제2항제4호에서 규정하는주된 투자대상자산으로 하여 투자대상자산의 가격 상승에 따른 투자 수익을 추구합니다.', '① 기본적으로 미래에셋인디아인프라섹터증권모투자신탁(주식)에 90% 이상 투자합니다. ② 모투자신탁에의 투자비중은 시장상황에 따라 전략적으로 결정됩니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (527, "미래에셋인디아인프라섹터증권자투자신탁 1(주식)", 3, 60, "['인프라펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (527, "KR5301714584", 3060.99, 127.36, 302.03, 10.65, 19.96, 47.5, 36.87, 70.61, 116.51, 153.83, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도 지역 주식에 주로 투자하는 모투자신탁을 법 시행령 제94조 제2항 제4호에서 규정하는 주된 투자대상자산으로 하여 투자대상자산의 가격 상승에 따른 투자 수익을 추구합니다.', '이 투자신탁이 투자하는 미래에셋 인디아 인프라 섹터 증권 모투자신탁(주식)의 투자전략은 다음과 같습니다. 1. 인도의 인프라관련 주식에 60% 이상 투자합니다. 2. 주식은 개별 기업의 가치 및 위험 등에 대한 내재적 가치 분석에 의한 운용 전략과 경제 환경 등에 대한 거시 경제 분석에 의한 운용 전략을 병행하여 적극적으로 운용합니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (528, "미래에셋TIGER필라델피아반도체레버리지증권상장지수투자신탁(주식혼합-파생형)(합성)", 3, 60, "['ETF(해외주식), 레버리지, 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (528, "K55301DS3146", 26382.79, 291.54, 2266.28, 26.77, 12.45, 100.88, 62.02, 115.56, null, null, 0.67, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "정보기술섹터", '이 투자신탁은 주식관련 파생상품 및 집합투자증권을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하며, 미국 주식으로 구성된 PHLX Semiconductor Sector 지수(원화환산)를 기초지수로하여 1좌당 순자산가치의 일간변 동률을 기초지수 일간변동률의 양의 2배수로 연동하여 투자신탁재산을 운용함을 목적으로 합니다.', '이 투자신탁은 Nasdaq에서 발표하는 PHLX Semiconductor Sector 지수(원화환산)를 기초지수로 하여 기초지수의 일간 수익률의 양의 2배수 수익률과 연동하는 것을 목적으로 하는 상장지수투자신탁으로서, 기초지수관련 파생상품(스왑) 및 집합투자증권을 주된 투자대상으로 하여 투자신탁의 일간수익률이 기초지수 일간수익률의 양의 2배수 수익률과 연동하도록 운용합니다.', '북미', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (529, "미래에셋TIGER미국나스닥100레버리지증권상장지수투자신탁(주식혼합-파생형)(합성)", 3, 60, "['ETF(해외주식), 레버리지, 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (529, "K55301DQ3577", 19057.81, 127.56, 657.49, 14.35, 8.72, 46.63, 35.38, 73.37, null, null, 0.37, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "북미주식", '이 투자신탁은 주식관련 파생상품 및 집합투자증권을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하며, 미국 주식으로 구성된 &#34;NASDAQ-100 지수(원화환산)&#34;를 기초지수로하여 1좌당 순자산가치의 일간변동률을 기초지 수 일간변동률의 양의 2배수로 연동하여 투자신탁재산을 운용함을 목적으로 합니다.', '이 투자신탁은 Nasdaq에서 발표하는 &#34;NASDAQ-100 지수(원화환산)&#34;를 기초지수로 하여 기초지수의 일간수익률의 양(陽) 의 2배수 수익률과 연동하는 것을 목적으로 하는 상장지수투자신탁으로서, 기초지수관련 파생상품(스왑 및 선물) 및 집합 투자증권을 주된 투자대상으로 하여 투자신탁의 일간수익률이 기초지수 일간수익률의 양의 2배수 수익률과 연동하도 록 운용합니다.', '북미', "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (530, "미래에셋인디아인프라섹터증권모투자신탁(주식)", 3, 60, "['인프라펀드, 모펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (530, "KR5301714576", 2775.85, 115.74, 1162.7, 10.67, 20.02, 47.72, 37.01, 70.98, 117.13, 154.86, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", '인도의 인프라관련 주식에 주로 투자하여 장기적인 자본이득을추구합니다주로 주식에 투자하는 주식형펀드로서 인디아의 인프라관련 주식에 투자하여 장기적인 자본이득을 추구합니다.', '자산총액의 60% 이상을 인도에 상장된 인프라 관련 주식에 투자합니다. 인프라 관련 주식이라 함은 사회기반시설과 관련된 산업재, 원자재, 에너지, 유틸리티, 통신 등 업종에 속한 주식을 뜻합니다. 벤치마크인 MSCI India Infra Index (Customized)의 수익률을 상회하는 투자 수익을 거두는 것을 목표로 합니다.', '인도', "상승", "좋음", "보통", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (531, "미래에셋연금인디아인프라증권자투자신탁 1(주식)", 3, 60, "['인프라펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (531, "K55301B25311", 1130.22, 46.91, 858.49, 10.61, 19.93, 47.54, 36.88, 70.68, 116.68, 154.21, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도 주식에 주로 투자하는 미래에셋인디아인프라섹터증권모투자신탁(주식)을 법 시행령 제94조제2항제4호에서 규정하는주된 투자대상자산으로 하여 투자대상자산의 가격 상승에 따른 투자 수익을 추구합니다.', '① 기본적으로 미래에셋인디아인프라섹터증권모투자신탁(주식)에 90% 이상 투자합니다. ② 모투자신탁에의 투자비중은 시장상황에 따라 전략적으로 결정됩니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (532, "미래에셋인디아인프라섹터증권자투자신탁 1(주식)", 3, 60, "['인프라펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (532, "KR5301714584", 3060.99, 127.36, 302.03, 10.65, 19.96, 47.5, 36.87, 70.61, 116.51, 153.83, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도 지역 주식에 주로 투자하는 모투자신탁을 법 시행령 제94조 제2항 제4호에서 규정하는 주된 투자대상자산으로 하여 투자대상자산의 가격 상승에 따른 투자 수익을 추구합니다.', '이 투자신탁이 투자하는 미래에셋 인디아 인프라 섹터 증권 모투자신탁(주식)의 투자전략은 다음과 같습니다. 1. 인도의 인프라관련 주식에 60% 이상 투자합니다. 2. 주식은 개별 기업의 가치 및 위험 등에 대한 내재적 가치 분석에 의한 운용 전략과 경제 환경 등에 대한 거시 경제 분석에 의한 운용 전략을 병행하여 적극적으로 운용합니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (533, "미래에셋TIGER필라델피아반도체레버리지증권상장지수투자신탁(주식혼합-파생형)(합성)", 3, 60, "['ETF(해외주식), 레버리지, 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (533, "K55301DS3146", 26382.79, 291.54, 2266.28, 26.77, 12.45, 100.88, 62.02, 115.56, null, null, 0.67, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "정보기술섹터", '이 투자신탁은 주식관련 파생상품 및 집합투자증권을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하며, 미국 주식으로 구성된 PHLX Semiconductor Sector 지수(원화환산)를 기초지수로하여 1좌당 순자산가치의 일간변 동률을 기초지수 일간변동률의 양의 2배수로 연동하여 투자신탁재산을 운용함을 목적으로 합니다.', '이 투자신탁은 Nasdaq에서 발표하는 PHLX Semiconductor Sector 지수(원화환산)를 기초지수로 하여 기초지수의 일간 수익률의 양의 2배수 수익률과 연동하는 것을 목적으로 하는 상장지수투자신탁으로서, 기초지수관련 파생상품(스왑) 및 집합투자증권을 주된 투자대상으로 하여 투자신탁의 일간수익률이 기초지수 일간수익률의 양의 2배수 수익률과 연동하도록 운용합니다.', '북미', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (534, "미래에셋TIGER미국나스닥100레버리지증권상장지수투자신탁(주식혼합-파생형)(합성)", 3, 60, "['ETF(해외주식), 레버리지, 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (534, "K55301DQ3577", 19057.81, 127.56, 657.49, 14.35, 8.72, 46.63, 35.38, 73.37, null, null, 0.37, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "북미주식", '이 투자신탁은 주식관련 파생상품 및 집합투자증권을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하며, 미국 주식으로 구성된 &#34;NASDAQ-100 지수(원화환산)&#34;를 기초지수로하여 1좌당 순자산가치의 일간변동률을 기초지 수 일간변동률의 양의 2배수로 연동하여 투자신탁재산을 운용함을 목적으로 합니다.', '이 투자신탁은 Nasdaq에서 발표하는 &#34;NASDAQ-100 지수(원화환산)&#34;를 기초지수로 하여 기초지수의 일간수익률의 양(陽) 의 2배수 수익률과 연동하는 것을 목적으로 하는 상장지수투자신탁으로서, 기초지수관련 파생상품(스왑 및 선물) 및 집합 투자증권을 주된 투자대상으로 하여 투자신탁의 일간수익률이 기초지수 일간수익률의 양의 2배수 수익률과 연동하도 록 운용합니다.', '북미', "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (535, "미래에셋인디아인프라섹터증권모투자신탁(주식)", 3, 60, "['인프라펀드, 모펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (535, "KR5301714576", 2775.85, 115.74, 1162.7, 10.67, 20.02, 47.72, 37.01, 70.98, 117.13, 154.86, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", '인도의 인프라관련 주식에 주로 투자하여 장기적인 자본이득을추구합니다주로 주식에 투자하는 주식형펀드로서 인디아의 인프라관련 주식에 투자하여 장기적인 자본이득을 추구합니다.', '자산총액의 60% 이상을 인도에 상장된 인프라 관련 주식에 투자합니다. 인프라 관련 주식이라 함은 사회기반시설과 관련된 산업재, 원자재, 에너지, 유틸리티, 통신 등 업종에 속한 주식을 뜻합니다. 벤치마크인 MSCI India Infra Index (Customized)의 수익률을 상회하는 투자 수익을 거두는 것을 목표로 합니다.', '인도', "상승", "좋음", "보통", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (536, "미래에셋연금인디아인프라증권자투자신탁 1(주식)", 3, 60, "['인프라펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (536, "K55301B25311", 1130.22, 46.91, 858.49, 10.61, 19.93, 47.54, 36.88, 70.68, 116.68, 154.21, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도 주식에 주로 투자하는 미래에셋인디아인프라섹터증권모투자신탁(주식)을 법 시행령 제94조제2항제4호에서 규정하는주된 투자대상자산으로 하여 투자대상자산의 가격 상승에 따른 투자 수익을 추구합니다.', '① 기본적으로 미래에셋인디아인프라섹터증권모투자신탁(주식)에 90% 이상 투자합니다. ② 모투자신탁에의 투자비중은 시장상황에 따라 전략적으로 결정됩니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (537, "미래에셋인디아인프라섹터증권자투자신탁 1(주식)", 3, 60, "['인프라펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (537, "KR5301714584", 3060.99, 127.36, 302.03, 10.65, 19.96, 47.5, 36.87, 70.61, 116.51, 153.83, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도 지역 주식에 주로 투자하는 모투자신탁을 법 시행령 제94조 제2항 제4호에서 규정하는 주된 투자대상자산으로 하여 투자대상자산의 가격 상승에 따른 투자 수익을 추구합니다.', '이 투자신탁이 투자하는 미래에셋 인디아 인프라 섹터 증권 모투자신탁(주식)의 투자전략은 다음과 같습니다. 1. 인도의 인프라관련 주식에 60% 이상 투자합니다. 2. 주식은 개별 기업의 가치 및 위험 등에 대한 내재적 가치 분석에 의한 운용 전략과 경제 환경 등에 대한 거시 경제 분석에 의한 운용 전략을 병행하여 적극적으로 운용합니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (538, "신한SOL한국형글로벌반도체액티브증권상장지수투자신탁[주식]", 3, 61, "['ETF(해외주식), 글로벌, 해외주식형, 해외지수, IT펀드']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (538, "K55210DS2478", 18833.21, 109.17, 504.73, 11.3, 9.13, 46.49, 33.09, 58.49, null, null, 0.96, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "정보기술섹터", '이 투자신탁은 주식을 법 시행령 제 94조 제 2항 제4호에서 규정하는 주된 투자대상자산으로 하며, S&P Global Semiconductor Korea Tilted Index(PR: Price Return)(원화환산)를 비교지수로 하여 1 좌당 순자 산가치의 변동률이 비교지수의 변동률을 초과하도록 운용함을 목적으로 합니다.', '비교지수인 S&P Global Semiconductor Korea Tilted Index(PR: Price Return)(원화환산)와  상관계수 유지를 위하여 비교지수 구성 종목인 국내외 주식을 주요 운용대상으로 하며, 투자목적 달성을 위하여 비교지수 또는 비교지수 이외의 구성종목과 장내외파생상품(스왑 등) 등에도 투자할 수 있습니다.', '글로벌', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (539, "신한인디아증권모투자신탁[주식]", 3, 61, "['모펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (539, "KR5210725481", 2850.62, 61.86, 290.92, 4.26, 9.83, 31.0, 24.55, 50.26, 81.28, 126.79, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", '신탁재산의 대부분을 투자하여 자산의 가치를 증대시키는 것을 주요 목적으로합니다.. 또한 파생상품 등을 활용하여 기준통화인 미국달러화 대비 원화의 환율변동위험에 대해 부분환헤지를 실행합니다.', '미국 달러화로 표시되고 인도 루피화등의 통화로 거래되는 인도 관련 주식등에 투자합니다. 따라서 당해 투자신탁은 환율변동위험에 노출됩니다. 미국 달러 대비 원화의 환율변동위험을 줄이기 위해서, 신한 BNP 파리바 투자신탁운용은 당해 투자신탁이 투자하는 모투자신탁의 기준가격 표시통화인 미국 달러화 대비 원화 환율변동위험에 대해, 거래비용과 유동성을 고려하여 파생상품을 이용한 부분헤지(목표헤지비율 80% 이상)를 실행합니다. 그러나 실제헤지비율은 이와 상이할 수 있습니다.', '인도', "하락", "좋음", "보통", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (540, "신한SOLAI반도체소부장증권상장지수투자신탁[주식]", 3, 61, "['국내주식형, MKF펀드, IT, ETF(국내주식)']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (540, "K55210E09965", 15731.5, -127.48, 3807.02, -0.48, 8.43, 20.27, 15.74, 45.64, null, null, 0.51, 2, "높은 위험", "선취(X),후취(X),환매(X)", "국내주식형", "인덱스주식기타", '이 투자신탁은 국내주식을 법 시행령 제94조 제2항 제4호에서 규정하는 주된 투자대상자산으로 하며 FnGuide 반도체 소부장 지수를 기초 지수로 하여 1 좌당 순자산가치의 변동률이 기초 지수의 변동률과 유사 하도록 운용함을 목적으로 합니다.', '이 집합투자기구는 FnGuide 에서 산출, 발표 하는 FnGuide 반도체 소부장 지수 (PR: Price Return)를 기초지수로 하는 상장지수투자신탁으로서, 투자목적 달성을 위해서 국내 주식시장에 상장된 주식에 투자신탁 자산총액의 60% 이상을 투자합니다.', null, "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (541, "신한동유럽플러스증권모투자신탁[주식]", 3, 61, "['모펀드, 신흥유럽, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (541, "KR5210663476", 947.28, 3.56, 93.63, 1.86, 8.9, 18.63, 15.55, 41.94, 9.33, 16.09, null, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥유럽주식", '중앙유럽, 동유럽, 터키 및 러시아 등 유럽의 신흥 국가의 주식 등에 주로 투자하는 모투자신탁에 신탁재산의 대부분을 투자하여 자산의 가치를 증대시키는 것을 주요 목적으로 하는 자투자신탁입니다.', '당해 투자신탁은 신흥유럽관련 주식 등에 투자하기 때문에 환율변동위험에 노출됩니다. 이러한 환율변동위험을 줄이기 위해서, 기준가격 표시통화인 미국 달러화 대비 원화 환율변동위험에 대해, 거래비용과 유동성을 고려하여 파생상품을 이용한 부분헤지(목표헤지비율 80% 이상)를 실행합니다. 그러나 실제헤지비율은 이와 상이할 수 있습니다.', '신흥유럽', "하락", "미흡", "낮음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (542, "신한동유럽플러스증권자투자신탁(H)[주식](종류)", 3, 61, "['신흥유럽, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (542, "KR5210662866", 671.13, 2.44, 96.77, 1.83, 8.72, 18.28, 15.26, 40.97, 0.11, 9.04, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥유럽주식", '이 집합투자기구는 유럽 신흥국가의 주식에 주로 투자하는 모투자신탁을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하여 수익을 추구하는 것을 목적으로 한다.', '이 투자신탁은 투자신탁재산의 대부분을 중앙유럽, 동유럽, 터키 및 러시아 등 유럽의 신흥 국가의 주식 등에 투자하는 모투자신탁의 수익증권에 투자합니다. 그리고 투자신탁재산의 10% 이하를 단기대출 및 금융기관에의 예치 등의 유동성 자산에 투자할 수 있습니다.', '신흥유럽', "하락", "미흡", "낮음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (543, "신한SOL한국형글로벌반도체액티브증권상장지수투자신탁[주식]", 3, 61, "['ETF(해외주식), 글로벌, 해외주식형, 해외지수, IT펀드']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (543, "K55210DS2478", 18833.21, 109.17, 504.73, 11.3, 9.13, 46.49, 33.09, 58.49, null, null, 0.96, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "정보기술섹터", '이 투자신탁은 주식을 법 시행령 제 94조 제 2항 제4호에서 규정하는 주된 투자대상자산으로 하며, S&P Global Semiconductor Korea Tilted Index(PR: Price Return)(원화환산)를 비교지수로 하여 1 좌당 순자 산가치의 변동률이 비교지수의 변동률을 초과하도록 운용함을 목적으로 합니다.', '비교지수인 S&P Global Semiconductor Korea Tilted Index(PR: Price Return)(원화환산)와  상관계수 유지를 위하여 비교지수 구성 종목인 국내외 주식을 주요 운용대상으로 하며, 투자목적 달성을 위하여 비교지수 또는 비교지수 이외의 구성종목과 장내외파생상품(스왑 등) 등에도 투자할 수 있습니다.', '글로벌', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (544, "신한인디아증권모투자신탁[주식]", 3, 61, "['모펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (544, "KR5210725481", 2850.62, 61.86, 290.92, 4.26, 9.83, 31.0, 24.55, 50.26, 81.28, 126.79, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", '신탁재산의 대부분을 투자하여 자산의 가치를 증대시키는 것을 주요 목적으로합니다.. 또한 파생상품 등을 활용하여 기준통화인 미국달러화 대비 원화의 환율변동위험에 대해 부분환헤지를 실행합니다.', '미국 달러화로 표시되고 인도 루피화등의 통화로 거래되는 인도 관련 주식등에 투자합니다. 따라서 당해 투자신탁은 환율변동위험에 노출됩니다. 미국 달러 대비 원화의 환율변동위험을 줄이기 위해서, 신한 BNP 파리바 투자신탁운용은 당해 투자신탁이 투자하는 모투자신탁의 기준가격 표시통화인 미국 달러화 대비 원화 환율변동위험에 대해, 거래비용과 유동성을 고려하여 파생상품을 이용한 부분헤지(목표헤지비율 80% 이상)를 실행합니다. 그러나 실제헤지비율은 이와 상이할 수 있습니다.', '인도', "하락", "좋음", "보통", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (545, "신한SOLAI반도체소부장증권상장지수투자신탁[주식]", 3, 61, "['국내주식형, MKF펀드, IT, ETF(국내주식)']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (545, "K55210E09965", 15731.5, -127.48, 3807.02, -0.48, 8.43, 20.27, 15.74, 45.64, null, null, 0.51, 2, "높은 위험", "선취(X),후취(X),환매(X)", "국내주식형", "인덱스주식기타", '이 투자신탁은 국내주식을 법 시행령 제94조 제2항 제4호에서 규정하는 주된 투자대상자산으로 하며 FnGuide 반도체 소부장 지수를 기초 지수로 하여 1 좌당 순자산가치의 변동률이 기초 지수의 변동률과 유사 하도록 운용함을 목적으로 합니다.', '이 집합투자기구는 FnGuide 에서 산출, 발표 하는 FnGuide 반도체 소부장 지수 (PR: Price Return)를 기초지수로 하는 상장지수투자신탁으로서, 투자목적 달성을 위해서 국내 주식시장에 상장된 주식에 투자신탁 자산총액의 60% 이상을 투자합니다.', null, "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (546, "신한동유럽플러스증권모투자신탁[주식]", 3, 61, "['모펀드, 신흥유럽, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (546, "KR5210663476", 947.28, 3.56, 93.63, 1.86, 8.9, 18.63, 15.55, 41.94, 9.33, 16.09, null, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥유럽주식", '중앙유럽, 동유럽, 터키 및 러시아 등 유럽의 신흥 국가의 주식 등에 주로 투자하는 모투자신탁에 신탁재산의 대부분을 투자하여 자산의 가치를 증대시키는 것을 주요 목적으로 하는 자투자신탁입니다.', '당해 투자신탁은 신흥유럽관련 주식 등에 투자하기 때문에 환율변동위험에 노출됩니다. 이러한 환율변동위험을 줄이기 위해서, 기준가격 표시통화인 미국 달러화 대비 원화 환율변동위험에 대해, 거래비용과 유동성을 고려하여 파생상품을 이용한 부분헤지(목표헤지비율 80% 이상)를 실행합니다. 그러나 실제헤지비율은 이와 상이할 수 있습니다.', '신흥유럽', "하락", "미흡", "낮음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (547, "신한동유럽플러스증권자투자신탁(H)[주식](종류)", 3, 61, "['신흥유럽, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (547, "KR5210662866", 671.13, 2.44, 96.77, 1.83, 8.72, 18.28, 15.26, 40.97, 0.11, 9.04, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥유럽주식", '이 집합투자기구는 유럽 신흥국가의 주식에 주로 투자하는 모투자신탁을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하여 수익을 추구하는 것을 목적으로 한다.', '이 투자신탁은 투자신탁재산의 대부분을 중앙유럽, 동유럽, 터키 및 러시아 등 유럽의 신흥 국가의 주식 등에 투자하는 모투자신탁의 수익증권에 투자합니다. 그리고 투자신탁재산의 10% 이하를 단기대출 및 금융기관에의 예치 등의 유동성 자산에 투자할 수 있습니다.', '신흥유럽', "하락", "미흡", "낮음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (548, "신한SOL한국형글로벌반도체액티브증권상장지수투자신탁[주식]", 3, 61, "['ETF(해외주식), 글로벌, 해외주식형, 해외지수, IT펀드']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (548, "K55210DS2478", 18833.21, 109.17, 504.73, 11.3, 9.13, 46.49, 33.09, 58.49, null, null, 0.96, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "정보기술섹터", '이 투자신탁은 주식을 법 시행령 제 94조 제 2항 제4호에서 규정하는 주된 투자대상자산으로 하며, S&P Global Semiconductor Korea Tilted Index(PR: Price Return)(원화환산)를 비교지수로 하여 1 좌당 순자 산가치의 변동률이 비교지수의 변동률을 초과하도록 운용함을 목적으로 합니다.', '비교지수인 S&P Global Semiconductor Korea Tilted Index(PR: Price Return)(원화환산)와  상관계수 유지를 위하여 비교지수 구성 종목인 국내외 주식을 주요 운용대상으로 하며, 투자목적 달성을 위하여 비교지수 또는 비교지수 이외의 구성종목과 장내외파생상품(스왑 등) 등에도 투자할 수 있습니다.', '글로벌', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (549, "신한인디아증권모투자신탁[주식]", 3, 61, "['모펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (549, "KR5210725481", 2850.62, 61.86, 290.92, 4.26, 9.83, 31.0, 24.55, 50.26, 81.28, 126.79, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", '신탁재산의 대부분을 투자하여 자산의 가치를 증대시키는 것을 주요 목적으로합니다.. 또한 파생상품 등을 활용하여 기준통화인 미국달러화 대비 원화의 환율변동위험에 대해 부분환헤지를 실행합니다.', '미국 달러화로 표시되고 인도 루피화등의 통화로 거래되는 인도 관련 주식등에 투자합니다. 따라서 당해 투자신탁은 환율변동위험에 노출됩니다. 미국 달러 대비 원화의 환율변동위험을 줄이기 위해서, 신한 BNP 파리바 투자신탁운용은 당해 투자신탁이 투자하는 모투자신탁의 기준가격 표시통화인 미국 달러화 대비 원화 환율변동위험에 대해, 거래비용과 유동성을 고려하여 파생상품을 이용한 부분헤지(목표헤지비율 80% 이상)를 실행합니다. 그러나 실제헤지비율은 이와 상이할 수 있습니다.', '인도', "하락", "좋음", "보통", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (550, "신한SOLAI반도체소부장증권상장지수투자신탁[주식]", 3, 61, "['국내주식형, MKF펀드, IT, ETF(국내주식)']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (550, "K55210E09965", 15731.5, -127.48, 3807.02, -0.48, 8.43, 20.27, 15.74, 45.64, null, null, 0.51, 2, "높은 위험", "선취(X),후취(X),환매(X)", "국내주식형", "인덱스주식기타", '이 투자신탁은 국내주식을 법 시행령 제94조 제2항 제4호에서 규정하는 주된 투자대상자산으로 하며 FnGuide 반도체 소부장 지수를 기초 지수로 하여 1 좌당 순자산가치의 변동률이 기초 지수의 변동률과 유사 하도록 운용함을 목적으로 합니다.', '이 집합투자기구는 FnGuide 에서 산출, 발표 하는 FnGuide 반도체 소부장 지수 (PR: Price Return)를 기초지수로 하는 상장지수투자신탁으로서, 투자목적 달성을 위해서 국내 주식시장에 상장된 주식에 투자신탁 자산총액의 60% 이상을 투자합니다.', null, "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (551, "신한동유럽플러스증권모투자신탁[주식]", 3, 61, "['모펀드, 신흥유럽, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (551, "KR5210663476", 947.28, 3.56, 93.63, 1.86, 8.9, 18.63, 15.55, 41.94, 9.33, 16.09, null, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥유럽주식", '중앙유럽, 동유럽, 터키 및 러시아 등 유럽의 신흥 국가의 주식 등에 주로 투자하는 모투자신탁에 신탁재산의 대부분을 투자하여 자산의 가치를 증대시키는 것을 주요 목적으로 하는 자투자신탁입니다.', '당해 투자신탁은 신흥유럽관련 주식 등에 투자하기 때문에 환율변동위험에 노출됩니다. 이러한 환율변동위험을 줄이기 위해서, 기준가격 표시통화인 미국 달러화 대비 원화 환율변동위험에 대해, 거래비용과 유동성을 고려하여 파생상품을 이용한 부분헤지(목표헤지비율 80% 이상)를 실행합니다. 그러나 실제헤지비율은 이와 상이할 수 있습니다.', '신흥유럽', "하락", "미흡", "낮음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (552, "신한동유럽플러스증권자투자신탁(H)[주식](종류)", 3, 61, "['신흥유럽, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (552, "KR5210662866", 671.13, 2.44, 96.77, 1.83, 8.72, 18.28, 15.26, 40.97, 0.11, 9.04, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥유럽주식", '이 집합투자기구는 유럽 신흥국가의 주식에 주로 투자하는 모투자신탁을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하여 수익을 추구하는 것을 목적으로 한다.', '이 투자신탁은 투자신탁재산의 대부분을 중앙유럽, 동유럽, 터키 및 러시아 등 유럽의 신흥 국가의 주식 등에 투자하는 모투자신탁의 수익증권에 투자합니다. 그리고 투자신탁재산의 10% 이하를 단기대출 및 금융기관에의 예치 등의 유동성 자산에 투자할 수 있습니다.', '신흥유럽', "하락", "미흡", "낮음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (553, "신한SOL한국형글로벌반도체액티브증권상장지수투자신탁[주식]", 3, 61, "['ETF(해외주식), 글로벌, 해외주식형, 해외지수, IT펀드']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (553, "K55210DS2478", 18833.21, 109.17, 504.73, 11.3, 9.13, 46.49, 33.09, 58.49, null, null, 0.96, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "정보기술섹터", '이 투자신탁은 주식을 법 시행령 제 94조 제 2항 제4호에서 규정하는 주된 투자대상자산으로 하며, S&P Global Semiconductor Korea Tilted Index(PR: Price Return)(원화환산)를 비교지수로 하여 1 좌당 순자 산가치의 변동률이 비교지수의 변동률을 초과하도록 운용함을 목적으로 합니다.', '비교지수인 S&P Global Semiconductor Korea Tilted Index(PR: Price Return)(원화환산)와  상관계수 유지를 위하여 비교지수 구성 종목인 국내외 주식을 주요 운용대상으로 하며, 투자목적 달성을 위하여 비교지수 또는 비교지수 이외의 구성종목과 장내외파생상품(스왑 등) 등에도 투자할 수 있습니다.', '글로벌', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (554, "신한인디아증권모투자신탁[주식]", 3, 61, "['모펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (554, "KR5210725481", 2850.62, 61.86, 290.92, 4.26, 9.83, 31.0, 24.55, 50.26, 81.28, 126.79, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", '신탁재산의 대부분을 투자하여 자산의 가치를 증대시키는 것을 주요 목적으로합니다.. 또한 파생상품 등을 활용하여 기준통화인 미국달러화 대비 원화의 환율변동위험에 대해 부분환헤지를 실행합니다.', '미국 달러화로 표시되고 인도 루피화등의 통화로 거래되는 인도 관련 주식등에 투자합니다. 따라서 당해 투자신탁은 환율변동위험에 노출됩니다. 미국 달러 대비 원화의 환율변동위험을 줄이기 위해서, 신한 BNP 파리바 투자신탁운용은 당해 투자신탁이 투자하는 모투자신탁의 기준가격 표시통화인 미국 달러화 대비 원화 환율변동위험에 대해, 거래비용과 유동성을 고려하여 파생상품을 이용한 부분헤지(목표헤지비율 80% 이상)를 실행합니다. 그러나 실제헤지비율은 이와 상이할 수 있습니다.', '인도', "하락", "좋음", "보통", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (555, "신한SOLAI반도체소부장증권상장지수투자신탁[주식]", 3, 61, "['국내주식형, MKF펀드, IT, ETF(국내주식)']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (555, "K55210E09965", 15731.5, -127.48, 3807.02, -0.48, 8.43, 20.27, 15.74, 45.64, null, null, 0.51, 2, "높은 위험", "선취(X),후취(X),환매(X)", "국내주식형", "인덱스주식기타", '이 투자신탁은 국내주식을 법 시행령 제94조 제2항 제4호에서 규정하는 주된 투자대상자산으로 하며 FnGuide 반도체 소부장 지수를 기초 지수로 하여 1 좌당 순자산가치의 변동률이 기초 지수의 변동률과 유사 하도록 운용함을 목적으로 합니다.', '이 집합투자기구는 FnGuide 에서 산출, 발표 하는 FnGuide 반도체 소부장 지수 (PR: Price Return)를 기초지수로 하는 상장지수투자신탁으로서, 투자목적 달성을 위해서 국내 주식시장에 상장된 주식에 투자신탁 자산총액의 60% 이상을 투자합니다.', null, "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (556, "신한동유럽플러스증권모투자신탁[주식]", 3, 61, "['모펀드, 신흥유럽, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (556, "KR5210663476", 947.28, 3.56, 93.63, 1.86, 8.9, 18.63, 15.55, 41.94, 9.33, 16.09, null, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥유럽주식", '중앙유럽, 동유럽, 터키 및 러시아 등 유럽의 신흥 국가의 주식 등에 주로 투자하는 모투자신탁에 신탁재산의 대부분을 투자하여 자산의 가치를 증대시키는 것을 주요 목적으로 하는 자투자신탁입니다.', '당해 투자신탁은 신흥유럽관련 주식 등에 투자하기 때문에 환율변동위험에 노출됩니다. 이러한 환율변동위험을 줄이기 위해서, 기준가격 표시통화인 미국 달러화 대비 원화 환율변동위험에 대해, 거래비용과 유동성을 고려하여 파생상품을 이용한 부분헤지(목표헤지비율 80% 이상)를 실행합니다. 그러나 실제헤지비율은 이와 상이할 수 있습니다.', '신흥유럽', "하락", "미흡", "낮음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (557, "신한동유럽플러스증권자투자신탁(H)[주식](종류)", 3, 61, "['신흥유럽, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (557, "KR5210662866", 671.13, 2.44, 96.77, 1.83, 8.72, 18.28, 15.26, 40.97, 0.11, 9.04, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥유럽주식", '이 집합투자기구는 유럽 신흥국가의 주식에 주로 투자하는 모투자신탁을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하여 수익을 추구하는 것을 목적으로 한다.', '이 투자신탁은 투자신탁재산의 대부분을 중앙유럽, 동유럽, 터키 및 러시아 등 유럽의 신흥 국가의 주식 등에 투자하는 모투자신탁의 수익증권에 투자합니다. 그리고 투자신탁재산의 10% 이하를 단기대출 및 금융기관에의 예치 등의 유동성 자산에 투자할 수 있습니다.', '신흥유럽', "하락", "미흡", "낮음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (558, "신한SOL한국형글로벌반도체액티브증권상장지수투자신탁[주식]", 3, 61, "['ETF(해외주식), 글로벌, 해외주식형, 해외지수, IT펀드']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (558, "K55210DS2478", 18833.21, 109.17, 504.73, 11.3, 9.13, 46.49, 33.09, 58.49, null, null, 0.96, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "정보기술섹터", '이 투자신탁은 주식을 법 시행령 제 94조 제 2항 제4호에서 규정하는 주된 투자대상자산으로 하며, S&P Global Semiconductor Korea Tilted Index(PR: Price Return)(원화환산)를 비교지수로 하여 1 좌당 순자 산가치의 변동률이 비교지수의 변동률을 초과하도록 운용함을 목적으로 합니다.', '비교지수인 S&P Global Semiconductor Korea Tilted Index(PR: Price Return)(원화환산)와  상관계수 유지를 위하여 비교지수 구성 종목인 국내외 주식을 주요 운용대상으로 하며, 투자목적 달성을 위하여 비교지수 또는 비교지수 이외의 구성종목과 장내외파생상품(스왑 등) 등에도 투자할 수 있습니다.', '글로벌', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (559, "신한인디아증권모투자신탁[주식]", 3, 61, "['모펀드, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (559, "KR5210725481", 2850.62, 61.86, 290.92, 4.26, 9.83, 31.0, 24.55, 50.26, 81.28, 126.79, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", '신탁재산의 대부분을 투자하여 자산의 가치를 증대시키는 것을 주요 목적으로합니다.. 또한 파생상품 등을 활용하여 기준통화인 미국달러화 대비 원화의 환율변동위험에 대해 부분환헤지를 실행합니다.', '미국 달러화로 표시되고 인도 루피화등의 통화로 거래되는 인도 관련 주식등에 투자합니다. 따라서 당해 투자신탁은 환율변동위험에 노출됩니다. 미국 달러 대비 원화의 환율변동위험을 줄이기 위해서, 신한 BNP 파리바 투자신탁운용은 당해 투자신탁이 투자하는 모투자신탁의 기준가격 표시통화인 미국 달러화 대비 원화 환율변동위험에 대해, 거래비용과 유동성을 고려하여 파생상품을 이용한 부분헤지(목표헤지비율 80% 이상)를 실행합니다. 그러나 실제헤지비율은 이와 상이할 수 있습니다.', '인도', "하락", "좋음", "보통", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (560, "신한SOLAI반도체소부장증권상장지수투자신탁[주식]", 3, 61, "['국내주식형, MKF펀드, IT, ETF(국내주식)']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (560, "K55210E09965", 15731.5, -127.48, 3807.02, -0.48, 8.43, 20.27, 15.74, 45.64, null, null, 0.51, 2, "높은 위험", "선취(X),후취(X),환매(X)", "국내주식형", "인덱스주식기타", '이 투자신탁은 국내주식을 법 시행령 제94조 제2항 제4호에서 규정하는 주된 투자대상자산으로 하며 FnGuide 반도체 소부장 지수를 기초 지수로 하여 1 좌당 순자산가치의 변동률이 기초 지수의 변동률과 유사 하도록 운용함을 목적으로 합니다.', '이 집합투자기구는 FnGuide 에서 산출, 발표 하는 FnGuide 반도체 소부장 지수 (PR: Price Return)를 기초지수로 하는 상장지수투자신탁으로서, 투자목적 달성을 위해서 국내 주식시장에 상장된 주식에 투자신탁 자산총액의 60% 이상을 투자합니다.', null, "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (561, "신한동유럽플러스증권모투자신탁[주식]", 3, 61, "['모펀드, 신흥유럽, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (561, "KR5210663476", 947.28, 3.56, 93.63, 1.86, 8.9, 18.63, 15.55, 41.94, 9.33, 16.09, null, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥유럽주식", '중앙유럽, 동유럽, 터키 및 러시아 등 유럽의 신흥 국가의 주식 등에 주로 투자하는 모투자신탁에 신탁재산의 대부분을 투자하여 자산의 가치를 증대시키는 것을 주요 목적으로 하는 자투자신탁입니다.', '당해 투자신탁은 신흥유럽관련 주식 등에 투자하기 때문에 환율변동위험에 노출됩니다. 이러한 환율변동위험을 줄이기 위해서, 기준가격 표시통화인 미국 달러화 대비 원화 환율변동위험에 대해, 거래비용과 유동성을 고려하여 파생상품을 이용한 부분헤지(목표헤지비율 80% 이상)를 실행합니다. 그러나 실제헤지비율은 이와 상이할 수 있습니다.', '신흥유럽', "하락", "미흡", "낮음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (562, "신한동유럽플러스증권자투자신탁(H)[주식](종류)", 3, 61, "['신흥유럽, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (562, "KR5210662866", 671.13, 2.44, 96.77, 1.83, 8.72, 18.28, 15.26, 40.97, 0.11, 9.04, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥유럽주식", '이 집합투자기구는 유럽 신흥국가의 주식에 주로 투자하는 모투자신탁을 법 시행령 제94조제2항제4호에서 규정하는 주된 투자대상자산으로 하여 수익을 추구하는 것을 목적으로 한다.', '이 투자신탁은 투자신탁재산의 대부분을 중앙유럽, 동유럽, 터키 및 러시아 등 유럽의 신흥 국가의 주식 등에 투자하는 모투자신탁의 수익증권에 투자합니다. 그리고 투자신탁재산의 10% 이하를 단기대출 및 금융기관에의 예치 등의 유동성 자산에 투자할 수 있습니다.', '신흥유럽', "하락", "미흡", "낮음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (563, "삼성KODEX미국반도체MV증권상장지수투자신탁[주식]", 3, 62, "['해외지수, ETF(해외주식), 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (563, "K55105DJ0265", 22962.95, 294.45, 4121.85, 15.85, 14.01, 60.06, 47.54, 75.95, null, null, 0.18, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "북미주식", '이 투자신탁은 미국거래소에 상장된 주식을 주된 투자대상으로 신탁재산의 60% 이상을 투자하며, 미국거래소에 상장된 반도체 기업을 대상으로, MV Index Solutions가 산출 및 발표하는 MVIS US Listed Semiconductor25 Index(KRW)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용할 계획입니다.', '이 투자신탁은 미국거래소에 상장된 반도체 기업을 대상으로, MV Index Solutions가 산출 및 발표하는 MVIS US Listed Semiconductor 25 Index(KRW)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용할', '북미', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (564, "삼성인도중소형FOCUS증권모투자신탁[주식]", 3, 62, "['모펀드, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (564, "K55105B33419", 4215.64, 139.72, 1861.38, 6.95, 13.4, 36.48, 30.97, 73.26, 146.25, 227.09, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", null, null, null, "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (565, "삼성인도중소형FOCUS증권자투자신탁UH[주식]", 3, 62, "['인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (565, "K55105B33534", 4177.42, 130.55, 989.93, 6.62, 12.87, 35.31, 29.95, 70.63, 140.88, 218.42, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다', '이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (566, "삼성클래식인도중소형FOCUS연금증권자투자신탁UH[주식]", 3, 62, "['연금저축, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (566, "K55105B78778", 4155.14, 129.87, 254.61, 6.62, 12.86, 35.24, 29.91, 70.62, 140.52, 217.01, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '가. 이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다. 나. 아울러 이 투자신탁은 해외투자에 따른 환율변동 위험 회피를 위한 환헷지 전략을 기본적으로 실시하지 않을 계획이므로 외국통화표시자산 투자에 따른 환위험에 노출됩니다.', '(1) 이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다. (2) 이 투자신탁은 해외투자로 인한 환율변동 위험을 회피하기 위한 환헷지 전략은 기본적으로 실시하지 않을 계획이므로 환율변동에 따른 위험에 노출되어 있습니다. 이로 인해 환율변동으로 인한 급격한 신탁재산 가치변동을 초래할 수 있으며, 심지어 투자신탁에서 투자한 증권의 가격상승으로 인한 수익이 발생하더라도 통화의 가치가 더 크게 하락할 경우 오히려 투자원본의 손실을 초래할 수도 있습니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (567, "삼성인도중소형FOCUS증권자투자신탁UH[주식]_Cw", 3, 62, "['인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (567, "K55105B33633", 4213.26, 131.62, 989.25, 6.54, 12.6, 34.74, 29.48, 69.26, 135.01, 205.03, 1.08, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다', '이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (568, "삼성KODEX미국반도체MV증권상장지수투자신탁[주식]", 3, 62, "['해외지수, ETF(해외주식), 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (568, "K55105DJ0265", 22962.95, 294.45, 4121.85, 15.85, 14.01, 60.06, 47.54, 75.95, null, null, 0.18, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "북미주식", '이 투자신탁은 미국거래소에 상장된 주식을 주된 투자대상으로 신탁재산의 60% 이상을 투자하며, 미국거래소에 상장된 반도체 기업을 대상으로, MV Index Solutions가 산출 및 발표하는 MVIS US Listed Semiconductor25 Index(KRW)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용할 계획입니다.', '이 투자신탁은 미국거래소에 상장된 반도체 기업을 대상으로, MV Index Solutions가 산출 및 발표하는 MVIS US Listed Semiconductor 25 Index(KRW)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용할', '북미', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (569, "삼성인도중소형FOCUS증권모투자신탁[주식]", 3, 62, "['모펀드, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (569, "K55105B33419", 4215.64, 139.72, 1861.38, 6.95, 13.4, 36.48, 30.97, 73.26, 146.25, 227.09, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", null, null, null, "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (570, "삼성인도중소형FOCUS증권자투자신탁UH[주식]", 3, 62, "['인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (570, "K55105B33534", 4177.42, 130.55, 989.93, 6.62, 12.87, 35.31, 29.95, 70.63, 140.88, 218.42, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다', '이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (571, "삼성클래식인도중소형FOCUS연금증권자투자신탁UH[주식]", 3, 62, "['연금저축, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (571, "K55105B78778", 4155.14, 129.87, 254.61, 6.62, 12.86, 35.24, 29.91, 70.62, 140.52, 217.01, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '가. 이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다. 나. 아울러 이 투자신탁은 해외투자에 따른 환율변동 위험 회피를 위한 환헷지 전략을 기본적으로 실시하지 않을 계획이므로 외국통화표시자산 투자에 따른 환위험에 노출됩니다.', '(1) 이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다. (2) 이 투자신탁은 해외투자로 인한 환율변동 위험을 회피하기 위한 환헷지 전략은 기본적으로 실시하지 않을 계획이므로 환율변동에 따른 위험에 노출되어 있습니다. 이로 인해 환율변동으로 인한 급격한 신탁재산 가치변동을 초래할 수 있으며, 심지어 투자신탁에서 투자한 증권의 가격상승으로 인한 수익이 발생하더라도 통화의 가치가 더 크게 하락할 경우 오히려 투자원본의 손실을 초래할 수도 있습니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (572, "삼성인도중소형FOCUS증권자투자신탁UH[주식]_Cw", 3, 62, "['인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (572, "K55105B33633", 4213.26, 131.62, 989.25, 6.54, 12.6, 34.74, 29.48, 69.26, 135.01, 205.03, 1.08, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다', '이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (573, "삼성KODEX미국반도체MV증권상장지수투자신탁[주식]", 3, 62, "['해외지수, ETF(해외주식), 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (573, "K55105DJ0265", 22962.95, 294.45, 4121.85, 15.85, 14.01, 60.06, 47.54, 75.95, null, null, 0.18, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "북미주식", '이 투자신탁은 미국거래소에 상장된 주식을 주된 투자대상으로 신탁재산의 60% 이상을 투자하며, 미국거래소에 상장된 반도체 기업을 대상으로, MV Index Solutions가 산출 및 발표하는 MVIS US Listed Semiconductor25 Index(KRW)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용할 계획입니다.', '이 투자신탁은 미국거래소에 상장된 반도체 기업을 대상으로, MV Index Solutions가 산출 및 발표하는 MVIS US Listed Semiconductor 25 Index(KRW)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용할', '북미', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (574, "삼성인도중소형FOCUS증권모투자신탁[주식]", 3, 62, "['모펀드, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (574, "K55105B33419", 4215.64, 139.72, 1861.38, 6.95, 13.4, 36.48, 30.97, 73.26, 146.25, 227.09, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", null, null, null, "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (575, "삼성인도중소형FOCUS증권자투자신탁UH[주식]", 3, 62, "['인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (575, "K55105B33534", 4177.42, 130.55, 989.93, 6.62, 12.87, 35.31, 29.95, 70.63, 140.88, 218.42, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다', '이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (576, "삼성클래식인도중소형FOCUS연금증권자투자신탁UH[주식]", 3, 62, "['연금저축, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (576, "K55105B78778", 4155.14, 129.87, 254.61, 6.62, 12.86, 35.24, 29.91, 70.62, 140.52, 217.01, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '가. 이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다. 나. 아울러 이 투자신탁은 해외투자에 따른 환율변동 위험 회피를 위한 환헷지 전략을 기본적으로 실시하지 않을 계획이므로 외국통화표시자산 투자에 따른 환위험에 노출됩니다.', '(1) 이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다. (2) 이 투자신탁은 해외투자로 인한 환율변동 위험을 회피하기 위한 환헷지 전략은 기본적으로 실시하지 않을 계획이므로 환율변동에 따른 위험에 노출되어 있습니다. 이로 인해 환율변동으로 인한 급격한 신탁재산 가치변동을 초래할 수 있으며, 심지어 투자신탁에서 투자한 증권의 가격상승으로 인한 수익이 발생하더라도 통화의 가치가 더 크게 하락할 경우 오히려 투자원본의 손실을 초래할 수도 있습니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (577, "삼성인도중소형FOCUS증권자투자신탁UH[주식]_Cw", 3, 62, "['인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (577, "K55105B33633", 4213.26, 131.62, 989.25, 6.54, 12.6, 34.74, 29.48, 69.26, 135.01, 205.03, 1.08, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다', '이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (578, "삼성KODEX미국반도체MV증권상장지수투자신탁[주식]", 3, 62, "['해외지수, ETF(해외주식), 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (578, "K55105DJ0265", 22962.95, 294.45, 4121.85, 15.85, 14.01, 60.06, 47.54, 75.95, null, null, 0.18, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "북미주식", '이 투자신탁은 미국거래소에 상장된 주식을 주된 투자대상으로 신탁재산의 60% 이상을 투자하며, 미국거래소에 상장된 반도체 기업을 대상으로, MV Index Solutions가 산출 및 발표하는 MVIS US Listed Semiconductor25 Index(KRW)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용할 계획입니다.', '이 투자신탁은 미국거래소에 상장된 반도체 기업을 대상으로, MV Index Solutions가 산출 및 발표하는 MVIS US Listed Semiconductor 25 Index(KRW)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용할', '북미', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (579, "삼성인도중소형FOCUS증권모투자신탁[주식]", 3, 62, "['모펀드, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (579, "K55105B33419", 4215.64, 139.72, 1861.38, 6.95, 13.4, 36.48, 30.97, 73.26, 146.25, 227.09, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", null, null, null, "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (580, "삼성인도중소형FOCUS증권자투자신탁UH[주식]", 3, 62, "['인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (580, "K55105B33534", 4177.42, 130.55, 989.93, 6.62, 12.87, 35.31, 29.95, 70.63, 140.88, 218.42, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다', '이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (581, "삼성클래식인도중소형FOCUS연금증권자투자신탁UH[주식]", 3, 62, "['연금저축, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (581, "K55105B78778", 4155.14, 129.87, 254.61, 6.62, 12.86, 35.24, 29.91, 70.62, 140.52, 217.01, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '가. 이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다. 나. 아울러 이 투자신탁은 해외투자에 따른 환율변동 위험 회피를 위한 환헷지 전략을 기본적으로 실시하지 않을 계획이므로 외국통화표시자산 투자에 따른 환위험에 노출됩니다.', '(1) 이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다. (2) 이 투자신탁은 해외투자로 인한 환율변동 위험을 회피하기 위한 환헷지 전략은 기본적으로 실시하지 않을 계획이므로 환율변동에 따른 위험에 노출되어 있습니다. 이로 인해 환율변동으로 인한 급격한 신탁재산 가치변동을 초래할 수 있으며, 심지어 투자신탁에서 투자한 증권의 가격상승으로 인한 수익이 발생하더라도 통화의 가치가 더 크게 하락할 경우 오히려 투자원본의 손실을 초래할 수도 있습니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (582, "삼성인도중소형FOCUS증권자투자신탁UH[주식]_Cw", 3, 62, "['인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (582, "K55105B33633", 4213.26, 131.62, 989.25, 6.54, 12.6, 34.74, 29.48, 69.26, 135.01, 205.03, 1.08, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다', '이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (583, "삼성KODEX미국반도체MV증권상장지수투자신탁[주식]", 3, 62, "['해외지수, ETF(해외주식), 북미, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (583, "K55105DJ0265", 22962.95, 294.45, 4121.85, 15.85, 14.01, 60.06, 47.54, 75.95, null, null, 0.18, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "북미주식", '이 투자신탁은 미국거래소에 상장된 주식을 주된 투자대상으로 신탁재산의 60% 이상을 투자하며, 미국거래소에 상장된 반도체 기업을 대상으로, MV Index Solutions가 산출 및 발표하는 MVIS US Listed Semiconductor25 Index(KRW)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용할 계획입니다.', '이 투자신탁은 미국거래소에 상장된 반도체 기업을 대상으로, MV Index Solutions가 산출 및 발표하는 MVIS US Listed Semiconductor 25 Index(KRW)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용할', '북미', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (584, "삼성인도중소형FOCUS증권모투자신탁[주식]", 3, 62, "['모펀드, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (584, "K55105B33419", 4215.64, 139.72, 1861.38, 6.95, 13.4, 36.48, 30.97, 73.26, 146.25, 227.09, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "신흥아시아주식", null, null, null, "하락", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (585, "삼성인도중소형FOCUS증권자투자신탁UH[주식]", 3, 62, "['인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (585, "K55105B33534", 4177.42, 130.55, 989.93, 6.62, 12.87, 35.31, 29.95, 70.63, 140.88, 218.42, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다', '이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (586, "삼성클래식인도중소형FOCUS연금증권자투자신탁UH[주식]", 3, 62, "['연금저축, 인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (586, "K55105B78778", 4155.14, 129.87, 254.61, 6.62, 12.86, 35.24, 29.91, 70.62, 140.52, 217.01, null, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '가. 이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다. 나. 아울러 이 투자신탁은 해외투자에 따른 환율변동 위험 회피를 위한 환헷지 전략을 기본적으로 실시하지 않을 계획이므로 외국통화표시자산 투자에 따른 환위험에 노출됩니다.', '(1) 이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다. (2) 이 투자신탁은 해외투자로 인한 환율변동 위험을 회피하기 위한 환헷지 전략은 기본적으로 실시하지 않을 계획이므로 환율변동에 따른 위험에 노출되어 있습니다. 이로 인해 환율변동으로 인한 급격한 신탁재산 가치변동을 초래할 수 있으며, 심지어 투자신탁에서 투자한 증권의 가격상승으로 인한 수익이 발생하더라도 통화의 가치가 더 크게 하락할 경우 오히려 투자원본의 손실을 초래할 수도 있습니다.', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (587, "삼성인도중소형FOCUS증권자투자신탁UH[주식]_Cw", 3, 62, "['인도, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (587, "K55105B33633", 4213.26, 131.62, 989.25, 6.54, 12.6, 34.74, 29.48, 69.26, 135.01, 205.03, 1.08, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "인도주식", '이 투자신탁은 인도의 중소형 기업과 향후 성장이 예상되는 기업 등의 주식에 주로 투자하는 모투자신탁에 투자신탁재산의 대부분을 투자하는 증권투자신탁[주식]으로 투자대상 자산의 가격상승 등에 따른 투자수익을 추구합니다', '이 투자신탁은 신탁재산의 70% 이상을 인도의 중소형기업과 향후 성장이 예상되는 기업 등이 발행하여 인도나 제3의 국가에서 상장되어 거래되는 주식 및 이를 기초로 한 증권예탁증권 등에 주로 투자하는 삼성인도중소형FOCUS모투자신탁[주식]에 투자하여 운용할 계획입니다', '인도', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (588, "KBKBSTAR글로벌원자력iSelect증권상장지수투자신탁(주식)", 3, 63, "['해외지수, ETF(해외주식), 글로벌, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (588, "K55223DX4944", 17790.97, -621.08, 320.24, 11.42, 25.81, 35.52, 36.51, 75.99, null, null, 0.79, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "글로벌주식", '이 투자신탁은 기초지수인 iSelect 글로벌원자력 지수 의 원화환산지수 환헤지 안함 ))’의 수익률과 유사한 수익률을 실현하는 것을 목표로 하는 상장지수투자신탁으로, 기초지수를 완전 복제하는 방식으로 운용할 계획입니다', '이 투자신탁은 NH투자증권이 산출·발표하는 iSelect 글로벌원자력 지수의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목표로 합니다.', '글로벌', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (589, "KBKBSTAR글로벌메타버스Moorgate증권상장지수투자신탁(주식)", 3, 63, "['해외지수, ETF(해외주식), 글로벌, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (589, "K55223DP1155", 14006.96, 205.83, 77.04, 11.5, 11.61, 39.36, 33.19, 53.99, null, null, 0.69, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "글로벌주식", '이 투자신탁은 전세계 주요국가에 상장된 메타버스 관련 주식을 법에서 정하는 주된 투자대상으로 하며, Moorgate Benchmarks Ltd에서 산출 및 발표하는 Global Metaverse Index의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목적으로 하는 투자신탁입니다.', '이 투자신탁은 Moorgate Benchmarks Ltd에서 산출 및 발표하는 Global Metaverse Index의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목표로 합니다.', '글로벌', "유지", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (590, "KBKBSTAR비메모리반도체액티브증권상장지수투자신탁(주식)", 3, 63, "['국내주식형, IT, ETF(국내주식)']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (590, "K55223DJ8901", 13735.98, -150.71, 3107.08, -0.92, 7.99, 22.41, 16.08, 46.25, null, null, 0.57, 2, "높은 위험", "선취(X),후취(X),환매(X)", "국내주식형", "인덱스주식기타", '이 투자신탁은 국내주식을 법에서 정하는 주된 투자대상으로 하며, NH투자증권에서 산출·발표하는 “iSelect 비메모리 반도체 지수”를 비교지수로 하여 비교지수 대비 초과수익 실현을 목적으로 하는 상장지수투자신탁입니다', '이 투자신탁은 NH투자증권이 산출·발표하는 “iSelect 비메모리반도체 지수”를 비교지수로 하는 액티브 상장지수 집합투자기구로, 비교지수 대비 초과수익 실현을 목표로 합니다', null, "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (591, "KB지수연계증권투자신탁230(ELS-파생형)(운용)", 3, 63, "['국내대체']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (591, "K55223DM7312", 1129.36, 5.63, 17.73, 2.45, 24.42, 32.95, 30.23, 43.45, null, null, null, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "국내대체", "ELF", '이 투자신탁은 파생결합증권을 주된 투자대상으로 하는 투자신탁으로서, S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 투자함으로써 수익을 추구하는 것을 목적으로 합니다.', '이 투자신탁은 주로 S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 집합투자재산의 대부분을 투자할 계획입니다.', null, "하락", "미흡", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (592, "KB지수연계증권투자신탁230(ELS-파생형)A", 3, 63, "['국내대체']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (592, "K55223DM7320", 1128.94, 5.62, 17.73, 2.45, 24.42, 32.95, 30.23, 43.45, null, null, 0.01, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "국내대체", "ELF", '이 투자신탁은 파생결합증권을 주된 투자대상으로 하는 투자신탁으로서, S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 투자함으로써 수익을 추구하는 것을 목적으로 합니다.', '이 투자신탁은 주로 S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 집합투자재산의 대부분을 투자할 계획입니다.', null, "하락", "미흡", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (593, "KBKBSTAR글로벌원자력iSelect증권상장지수투자신탁(주식)", 3, 63, "['해외지수, ETF(해외주식), 글로벌, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (593, "K55223DX4944", 17790.97, -621.08, 320.24, 11.42, 25.81, 35.52, 36.51, 75.99, null, null, 0.79, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "글로벌주식", '이 투자신탁은 기초지수인 iSelect 글로벌원자력 지수 의 원화환산지수 환헤지 안함 ))’의 수익률과 유사한 수익률을 실현하는 것을 목표로 하는 상장지수투자신탁으로, 기초지수를 완전 복제하는 방식으로 운용할 계획입니다', '이 투자신탁은 NH투자증권이 산출·발표하는 iSelect 글로벌원자력 지수의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목표로 합니다.', '글로벌', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (594, "KBKBSTAR글로벌메타버스Moorgate증권상장지수투자신탁(주식)", 3, 63, "['해외지수, ETF(해외주식), 글로벌, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (594, "K55223DP1155", 14006.96, 205.83, 77.04, 11.5, 11.61, 39.36, 33.19, 53.99, null, null, 0.69, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "글로벌주식", '이 투자신탁은 전세계 주요국가에 상장된 메타버스 관련 주식을 법에서 정하는 주된 투자대상으로 하며, Moorgate Benchmarks Ltd에서 산출 및 발표하는 Global Metaverse Index의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목적으로 하는 투자신탁입니다.', '이 투자신탁은 Moorgate Benchmarks Ltd에서 산출 및 발표하는 Global Metaverse Index의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목표로 합니다.', '글로벌', "유지", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (595, "KBKBSTAR비메모리반도체액티브증권상장지수투자신탁(주식)", 3, 63, "['국내주식형, IT, ETF(국내주식)']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (595, "K55223DJ8901", 13735.98, -150.71, 3107.08, -0.92, 7.99, 22.41, 16.08, 46.25, null, null, 0.57, 2, "높은 위험", "선취(X),후취(X),환매(X)", "국내주식형", "인덱스주식기타", '이 투자신탁은 국내주식을 법에서 정하는 주된 투자대상으로 하며, NH투자증권에서 산출·발표하는 “iSelect 비메모리 반도체 지수”를 비교지수로 하여 비교지수 대비 초과수익 실현을 목적으로 하는 상장지수투자신탁입니다', '이 투자신탁은 NH투자증권이 산출·발표하는 “iSelect 비메모리반도체 지수”를 비교지수로 하는 액티브 상장지수 집합투자기구로, 비교지수 대비 초과수익 실현을 목표로 합니다', null, "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (596, "KB지수연계증권투자신탁230(ELS-파생형)(운용)", 3, 63, "['국내대체']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (596, "K55223DM7312", 1129.36, 5.63, 17.73, 2.45, 24.42, 32.95, 30.23, 43.45, null, null, null, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "국내대체", "ELF", '이 투자신탁은 파생결합증권을 주된 투자대상으로 하는 투자신탁으로서, S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 투자함으로써 수익을 추구하는 것을 목적으로 합니다.', '이 투자신탁은 주로 S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 집합투자재산의 대부분을 투자할 계획입니다.', null, "하락", "미흡", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (597, "KB지수연계증권투자신탁230(ELS-파생형)A", 3, 63, "['국내대체']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (597, "K55223DM7320", 1128.94, 5.62, 17.73, 2.45, 24.42, 32.95, 30.23, 43.45, null, null, 0.01, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "국내대체", "ELF", '이 투자신탁은 파생결합증권을 주된 투자대상으로 하는 투자신탁으로서, S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 투자함으로써 수익을 추구하는 것을 목적으로 합니다.', '이 투자신탁은 주로 S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 집합투자재산의 대부분을 투자할 계획입니다.', null, "하락", "미흡", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (598, "KBKBSTAR글로벌원자력iSelect증권상장지수투자신탁(주식)", 3, 63, "['해외지수, ETF(해외주식), 글로벌, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (598, "K55223DX4944", 17790.97, -621.08, 320.24, 11.42, 25.81, 35.52, 36.51, 75.99, null, null, 0.79, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "글로벌주식", '이 투자신탁은 기초지수인 iSelect 글로벌원자력 지수 의 원화환산지수 환헤지 안함 ))’의 수익률과 유사한 수익률을 실현하는 것을 목표로 하는 상장지수투자신탁으로, 기초지수를 완전 복제하는 방식으로 운용할 계획입니다', '이 투자신탁은 NH투자증권이 산출·발표하는 iSelect 글로벌원자력 지수의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목표로 합니다.', '글로벌', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (599, "KBKBSTAR글로벌메타버스Moorgate증권상장지수투자신탁(주식)", 3, 63, "['해외지수, ETF(해외주식), 글로벌, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (599, "K55223DP1155", 14006.96, 205.83, 77.04, 11.5, 11.61, 39.36, 33.19, 53.99, null, null, 0.69, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "글로벌주식", '이 투자신탁은 전세계 주요국가에 상장된 메타버스 관련 주식을 법에서 정하는 주된 투자대상으로 하며, Moorgate Benchmarks Ltd에서 산출 및 발표하는 Global Metaverse Index의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목적으로 하는 투자신탁입니다.', '이 투자신탁은 Moorgate Benchmarks Ltd에서 산출 및 발표하는 Global Metaverse Index의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목표로 합니다.', '글로벌', "유지", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (600, "KBKBSTAR비메모리반도체액티브증권상장지수투자신탁(주식)", 3, 63, "['국내주식형, IT, ETF(국내주식)']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (600, "K55223DJ8901", 13735.98, -150.71, 3107.08, -0.92, 7.99, 22.41, 16.08, 46.25, null, null, 0.57, 2, "높은 위험", "선취(X),후취(X),환매(X)", "국내주식형", "인덱스주식기타", '이 투자신탁은 국내주식을 법에서 정하는 주된 투자대상으로 하며, NH투자증권에서 산출·발표하는 “iSelect 비메모리 반도체 지수”를 비교지수로 하여 비교지수 대비 초과수익 실현을 목적으로 하는 상장지수투자신탁입니다', '이 투자신탁은 NH투자증권이 산출·발표하는 “iSelect 비메모리반도체 지수”를 비교지수로 하는 액티브 상장지수 집합투자기구로, 비교지수 대비 초과수익 실현을 목표로 합니다', null, "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (601, "KB지수연계증권투자신탁230(ELS-파생형)(운용)", 3, 63, "['국내대체']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (601, "K55223DM7312", 1129.36, 5.63, 17.73, 2.45, 24.42, 32.95, 30.23, 43.45, null, null, null, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "국내대체", "ELF", '이 투자신탁은 파생결합증권을 주된 투자대상으로 하는 투자신탁으로서, S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 투자함으로써 수익을 추구하는 것을 목적으로 합니다.', '이 투자신탁은 주로 S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 집합투자재산의 대부분을 투자할 계획입니다.', null, "하락", "미흡", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (602, "KB지수연계증권투자신탁230(ELS-파생형)A", 3, 63, "['국내대체']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (602, "K55223DM7320", 1128.94, 5.62, 17.73, 2.45, 24.42, 32.95, 30.23, 43.45, null, null, 0.01, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "국내대체", "ELF", '이 투자신탁은 파생결합증권을 주된 투자대상으로 하는 투자신탁으로서, S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 투자함으로써 수익을 추구하는 것을 목적으로 합니다.', '이 투자신탁은 주로 S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 집합투자재산의 대부분을 투자할 계획입니다.', null, "하락", "미흡", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (603, "KBKBSTAR글로벌원자력iSelect증권상장지수투자신탁(주식)", 3, 63, "['해외지수, ETF(해외주식), 글로벌, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (603, "K55223DX4944", 17790.97, -621.08, 320.24, 11.42, 25.81, 35.52, 36.51, 75.99, null, null, 0.79, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "글로벌주식", '이 투자신탁은 기초지수인 iSelect 글로벌원자력 지수 의 원화환산지수 환헤지 안함 ))’의 수익률과 유사한 수익률을 실현하는 것을 목표로 하는 상장지수투자신탁으로, 기초지수를 완전 복제하는 방식으로 운용할 계획입니다', '이 투자신탁은 NH투자증권이 산출·발표하는 iSelect 글로벌원자력 지수의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목표로 합니다.', '글로벌', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (604, "KBKBSTAR글로벌메타버스Moorgate증권상장지수투자신탁(주식)", 3, 63, "['해외지수, ETF(해외주식), 글로벌, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (604, "K55223DP1155", 14006.96, 205.83, 77.04, 11.5, 11.61, 39.36, 33.19, 53.99, null, null, 0.69, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "글로벌주식", '이 투자신탁은 전세계 주요국가에 상장된 메타버스 관련 주식을 법에서 정하는 주된 투자대상으로 하며, Moorgate Benchmarks Ltd에서 산출 및 발표하는 Global Metaverse Index의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목적으로 하는 투자신탁입니다.', '이 투자신탁은 Moorgate Benchmarks Ltd에서 산출 및 발표하는 Global Metaverse Index의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목표로 합니다.', '글로벌', "유지", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (605, "KBKBSTAR비메모리반도체액티브증권상장지수투자신탁(주식)", 3, 63, "['국내주식형, IT, ETF(국내주식)']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (605, "K55223DJ8901", 13735.98, -150.71, 3107.08, -0.92, 7.99, 22.41, 16.08, 46.25, null, null, 0.57, 2, "높은 위험", "선취(X),후취(X),환매(X)", "국내주식형", "인덱스주식기타", '이 투자신탁은 국내주식을 법에서 정하는 주된 투자대상으로 하며, NH투자증권에서 산출·발표하는 “iSelect 비메모리 반도체 지수”를 비교지수로 하여 비교지수 대비 초과수익 실현을 목적으로 하는 상장지수투자신탁입니다', '이 투자신탁은 NH투자증권이 산출·발표하는 “iSelect 비메모리반도체 지수”를 비교지수로 하는 액티브 상장지수 집합투자기구로, 비교지수 대비 초과수익 실현을 목표로 합니다', null, "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (606, "KB지수연계증권투자신탁230(ELS-파생형)(운용)", 3, 63, "['국내대체']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (606, "K55223DM7312", 1129.36, 5.63, 17.73, 2.45, 24.42, 32.95, 30.23, 43.45, null, null, null, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "국내대체", "ELF", '이 투자신탁은 파생결합증권을 주된 투자대상으로 하는 투자신탁으로서, S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 투자함으로써 수익을 추구하는 것을 목적으로 합니다.', '이 투자신탁은 주로 S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 집합투자재산의 대부분을 투자할 계획입니다.', null, "하락", "미흡", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (607, "KB지수연계증권투자신탁230(ELS-파생형)A", 3, 63, "['국내대체']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (607, "K55223DM7320", 1128.94, 5.62, 17.73, 2.45, 24.42, 32.95, 30.23, 43.45, null, null, 0.01, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "국내대체", "ELF", '이 투자신탁은 파생결합증권을 주된 투자대상으로 하는 투자신탁으로서, S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 투자함으로써 수익을 추구하는 것을 목적으로 합니다.', '이 투자신탁은 주로 S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 집합투자재산의 대부분을 투자할 계획입니다.', null, "하락", "미흡", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (608, "KBKBSTAR글로벌원자력iSelect증권상장지수투자신탁(주식)", 3, 63, "['해외지수, ETF(해외주식), 글로벌, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (608, "K55223DX4944", 17790.97, -621.08, 320.24, 11.42, 25.81, 35.52, 36.51, 75.99, null, null, 0.79, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "글로벌주식", '이 투자신탁은 기초지수인 iSelect 글로벌원자력 지수 의 원화환산지수 환헤지 안함 ))’의 수익률과 유사한 수익률을 실현하는 것을 목표로 하는 상장지수투자신탁으로, 기초지수를 완전 복제하는 방식으로 운용할 계획입니다', '이 투자신탁은 NH투자증권이 산출·발표하는 iSelect 글로벌원자력 지수의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목표로 합니다.', '글로벌', "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (609, "KBKBSTAR글로벌메타버스Moorgate증권상장지수투자신탁(주식)", 3, 63, "['해외지수, ETF(해외주식), 글로벌, 해외주식형']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (609, "K55223DP1155", 14006.96, 205.83, 77.04, 11.5, 11.61, 39.36, 33.19, 53.99, null, null, 0.69, 2, "높은 위험", "선취(X),후취(X),환매(X)", "해외주식형", "글로벌주식", '이 투자신탁은 전세계 주요국가에 상장된 메타버스 관련 주식을 법에서 정하는 주된 투자대상으로 하며, Moorgate Benchmarks Ltd에서 산출 및 발표하는 Global Metaverse Index의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목적으로 하는 투자신탁입니다.', '이 투자신탁은 Moorgate Benchmarks Ltd에서 산출 및 발표하는 Global Metaverse Index의 원화환산지수(환헤지 안함)를 기초지수로 하여 1좌당 순자산가치의 변동률을 기초지수의 변동률과 유사하도록 투자신탁재산을 운용하는 것을 목표로 합니다.', '글로벌', "유지", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (610, "KBKBSTAR비메모리반도체액티브증권상장지수투자신탁(주식)", 3, 63, "['국내주식형, IT, ETF(국내주식)']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (610, "K55223DJ8901", 13735.98, -150.71, 3107.08, -0.92, 7.99, 22.41, 16.08, 46.25, null, null, 0.57, 2, "높은 위험", "선취(X),후취(X),환매(X)", "국내주식형", "인덱스주식기타", '이 투자신탁은 국내주식을 법에서 정하는 주된 투자대상으로 하며, NH투자증권에서 산출·발표하는 “iSelect 비메모리 반도체 지수”를 비교지수로 하여 비교지수 대비 초과수익 실현을 목적으로 하는 상장지수투자신탁입니다', '이 투자신탁은 NH투자증권이 산출·발표하는 “iSelect 비메모리반도체 지수”를 비교지수로 하는 액티브 상장지수 집합투자기구로, 비교지수 대비 초과수익 실현을 목표로 합니다', null, "상승", "좋음", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (611, "KB지수연계증권투자신탁230(ELS-파생형)(운용)", 3, 63, "['국내대체']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (611, "K55223DM7312", 1129.36, 5.63, 17.73, 2.45, 24.42, 32.95, 30.23, 43.45, null, null, null, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "국내대체", "ELF", '이 투자신탁은 파생결합증권을 주된 투자대상으로 하는 투자신탁으로서, S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 투자함으로써 수익을 추구하는 것을 목적으로 합니다.', '이 투자신탁은 주로 S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 집합투자재산의 대부분을 투자할 계획입니다.', null, "하락", "미흡", "높음", "좋음");

INSERT INTO product(id, name, category_id, corp_id, tags, card_image, created_at, modified_at)
VALUES (612, "KB지수연계증권투자신탁230(ELS-파생형)A", 3, 63, "['국내대체']", null, now(), now());
INSERT INTO fund (product_id, fund_code, std_price, diff_price, drv_nav, rt_1m, rt_3m, rt_6m, rt_ytd, rt_1y, rt_3y, rt_5y, ter, risk_grade, risk_grade_text, fee_gb, category1, category2, info_object, info_strategy, region, amt_gb, exce_bm, risk_gb, rt_gb)
VALUES (612, "K55223DM7320", 1128.94, 5.62, 17.73, 2.45, 24.42, 32.95, 30.23, 43.45, null, null, 0.01, 1, "매우 높은 위험", "선취(X),후취(X),환매(X)", "국내대체", "ELF", '이 투자신탁은 파생결합증권을 주된 투자대상으로 하는 투자신탁으로서, S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 투자함으로써 수익을 추구하는 것을 목적으로 합니다.', '이 투자신탁은 주로 S&P500 지수, HSCEI 지수, EUROSTOXX50 지수의 변동에 연계되고 매 6개월 단위로 조기상환조건이 내재된 파생결합증권에 집합투자재산의 대부분을 투자할 계획입니다.', null, "하락", "미흡", "높음", "좋음");
