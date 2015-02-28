function  fetchLevel()
%FETCHLEVEL fetch the level info from source data file
%   From source data file, fetch the level data, and construct the map info 

file_name = 'filter_rds_0226_3';
[mac, level] = textread(file_name, '%s\t%d');

map_info = containers.Map();

for ii=1:length(mac)
    if map_info.isKey(mac{ii})
        map_info(mac{ii}) = [map_info(mac{ii}); level(ii)];
    else
        map_info(mac{ii}) = level(ii);
    end
end

map_info_rds_0226_3 = map_info;
mac_rds_0226_3 = mac;
level_rds_0226_3 = level;

save('map_data_0226', '-append', 'map_info_rds_0226_3', 'mac_rds_0226_3', 'level_rds_0226_3');
% save('map_data_0226', 'map_info_tv_0226_1', 'mac_tv_0226_1', 'level_tv_0226_1');

end

