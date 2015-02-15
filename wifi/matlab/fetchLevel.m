function  fetchLevel()
%FETCHLEVEL fetch the level info from source data file
%   From source data file, fetch the level data, and construct the map info 

file_name = 'filter_0_31';
[mac, level] = textread(file_name, '%s\t%d');

map_info = containers.Map();

for ii=1:length(mac)
    if map_info.isKey(mac{ii})
        map_info(mac{ii}) = [map_info(mac{ii}); level(ii)];
    else
        map_info(mac{ii}) = level(ii);
    end
end

map_info_0_31 = map_info;
mac_0_31 = mac;
level_0_31 = level;

save('map_data_0129', '-append', 'map_info_0_31', 'mac_0_31', 'level_0_31');

end

