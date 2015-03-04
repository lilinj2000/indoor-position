#!/usr/bin/env python
# coding: utf-8

"""standard normal distribution algo"""

__author__ = "Linjiang Li (lilinj2000@gmail.com)"
__version__ = "$Revision: 1.0 $"
__date__ = "$Date: 2015/3/4 14:40:20 $"
__copyright__ = "Copyright (c) 2001 Linjiang Li"
__license__ = "Python"

def stdnormdist(z):
    """STDNORMDIST search stdard normal distribution table"""

    std_tab = ( 
        (0, 0.5), (0.13, 0.55),
        (0.25, 0.6), (0.53, 0.7),
        (0.68, 0.75), (0.85, 0.8),
        (1.04, 0.85), (1.29, 0.9),
        (1.64, 0.95), (3.9, 1) )

    abs_z = abs(z)

    for i in range(len(std_tab)-1, -1, -1):
        (v, p) = std_tab[i]
        if abs_z>= v:
            if z>0:
                return p
            else:
                return 1-p

if __name__ == "__main__":
    print stdnormdist(2)
    print stdnormdist(-2)
