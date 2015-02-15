function  mergeMaps( )
%MERGEMAPS Merge the two map info
%   mrege map info

load map_data_0129;

map_info = map_info_0_0_01;
map_info_01 = map_info_0_1;

keys_01 = map_info_01.keys();

for ii = 1:length(keys_01)
    if map_info.isKey(keys_01{ii})
        map_info(keys_01{ii}) = [map_info(keys_01{ii}); map_info_01(keys_01{ii})];
    else
        map_info(keys_01{ii}) = map_info_01(keys_01{ii});
    end
end

map_info_0_0_01_1 = map_info;
save('map_data_0129', '-append', 'map_info_0_0_01_1');

end

