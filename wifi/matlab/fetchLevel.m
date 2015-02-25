function  fetchLevel()
%FETCHLEVEL fetch the level info from source data file
%   From source data file, fetch the level data, and construct the map info 

file_name = 'filter_tv_0216_3';
[mac, level] = textread(file_name, '%s\t%d');

map_info = containers.Map();

for ii=1:length(mac)
    if map_info.isKey(mac{ii})
        map_info(mac{ii}) = [map_info(mac{ii}); level(ii)];
    else
        map_info(mac{ii}) = level(ii);
    end
end

map_info_tv_0216_3 = map_info;
mac_tv_0216_3 = mac;
level_tv_0216_3 = level;

save('map_data_0216', '-append', 'map_info_tv_0216_3', 'mac_tv_0216_3', 'level_tv_0216_3');
% save('map_data_0216', 'map_info_rds_0216_1', 'mac_rds_0216_1', 'level_rds_0216_1');

end

