CREATE TABLE BSK_SKIN_SALE_HISTORY_BY_DAY (
	IDT_BSK_SKIN_SALE_HISTORY BIGSERIAL NOT NULL,
	DAT_OCCURRENCE TIMESTAMP NOT NULL,
	IND_TYPE INTEGER NOT NULL,
	NUM_PRICE DECIMAL(23, 4),
	COD_SKIN VARCHAR(255)
) PARTITION BY RANGE(DAT_OCCURRENCE, IND_TYPE);

CREATE INDEX BSK_SKIN_SALE_HISTORY_BY_DAY_IDX_1 ON BSK_SKIN_SALE_HISTORY_BY_DAY(DAT_OCCURRENCE, IND_TYPE);
CREATE INDEX BSK_SKIN_SALE_HISTORY_BY_DAY_IDX2 ON BSK_SKIN_SALE_HISTORY_BY_DAY(IDT_BSK_SKIN_SALE_HISTORY);

CREATE OR REPLACE FUNCTION createSkinSaleByDayPartition (dateFrom TEXT, dateTo TEXT, type INTEGER)
RETURNS TEXT AS $func$
DECLARE
	partitionName text;
BEGIN
	partitionName := 'BSK_SKIN_SALE_HISTORY_BY_DAY' || type || '_' || REPLACE(dateFrom, '-', '_');
	EXECUTE (
		'CREATE TABLE ' || partitionName || ' PARTITION OF BSK_SKIN_SALE_HISTORY_BY_DAY
			FOR VALUES FROM ('''|| dateFrom || ''', ' || type || ') TO (''' || dateTo || ''', ' || type || ');'
	);
	RETURN 'partition created: ' || partitionName;
END;
$func$ LANGUAGE plpgsql;
