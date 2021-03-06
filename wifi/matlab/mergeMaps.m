function  mergeMaps( )
%MERGEMAPS Merge the two map info
%   mrege map info

load map_data_0226;

map_info = map_info_rds_0226_1;
map_info_01 = map_info_rds_0226_2;

keys_01 = map_info_01.keys();

for ii = 1:length(keys_01)
    if map_info.isKey(keys_01{ii})
        map_info(keys_01{ii}) = [map_info(keys_01{ii}); map_info_01(keys_01{ii})];
    else
        map_info(keys_01{ii}) = map_info_01(keys_01{ii});
    end
end

map_info_02 = map_info_rds_0226_3;

keys_02 = map_info_02.keys();
for ii = 1:length(keys_02)
    if map_info.isKey(keys_02{ii})
        map_info(keys_02{ii}) = [map_info(keys_02{ii}); map_info_02(keys_02{ii})];
    else
        map_info(keys_02{ii}) = map_info_02(keys_02{ii});
    end
end

map_info_rds_0226 = map_info;
save('map_data_0226', '-append', 'map_info_rds_0226');

end

